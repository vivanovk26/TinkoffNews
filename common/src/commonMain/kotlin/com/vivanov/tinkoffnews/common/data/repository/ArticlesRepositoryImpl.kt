package com.vivanov.tinkoffnews.common.data.repository

import com.vivanov.tinkoffnews.common.data.api.services.ApiService
import com.vivanov.tinkoffnews.common.data.database.services.DatabaseService
import com.vivanov.tinkoffnews.common.domain.model.Article
import com.vivanov.tinkoffnews.common.domain.model.DatabaseState

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesRepositoryImpl(
    private val apiService: ApiService,
    private val databaseService: DatabaseService
) : ArticlesRepository {

    private val cachedArticles: MutableList<Article> = mutableListOf()

    override suspend fun getArticlesFromApi(searchText: String): List<Article> {
        return if (searchText.isBlank()) {
            cachedArticles.clear()
            val articles = apiService.getArticles()
            articles.forEach { article -> article.databaseState = getArticleState(article) }
            cachedArticles.addAll(articles)
            cachedArticles
        } else {
            cachedArticles.filter { article -> article.name.contains(searchText) || article.text.contains(searchText) }
        }
    }

    private suspend fun getArticleState(article: Article): DatabaseState {
        return when (article.id) {
            in databaseService.getAllArticleIds() -> DatabaseState.IN_DATABASE
            else -> DatabaseState.OUT_DATABASE
        }
    }

    override suspend fun getArticlesFromDb(): List<Article> {
        return databaseService.getAllArticles()
    }

    override suspend fun updateArticle(article: Article): Article {
        when (article.databaseState) {
            DatabaseState.IN_DATABASE -> {
                databaseService.deleteArticle(article.id)
                article.databaseState = DatabaseState.OUT_DATABASE // TODO possible error
            }
            DatabaseState.OUT_DATABASE -> {
                databaseService.insertArticle(article)
                article.databaseState = DatabaseState.IN_DATABASE // TODO possible error
            }
            else -> Unit
        }
        return article
    }
}
