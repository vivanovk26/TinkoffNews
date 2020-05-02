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
            val articles = apiService.getArticles(databaseService.getAllArticleIds())
            cachedArticles.addAll(articles)
            cachedArticles
        } else {
            cachedArticles.filter { article -> article.name.contains(searchText) || article.text.contains(searchText) }
        }
    }

    override suspend fun getArticlesFromDb(): List<Article> {
        return databaseService.getAllArticles()
    }

    override suspend fun updateArticle(article: Article): Article {
        return when (article.databaseState) {
            DatabaseState.IN_DATABASE -> {
                databaseService.deleteArticle(article.id)
                article.copyDatabaseState(DatabaseState.OUT_DATABASE)
            }
            DatabaseState.OUT_DATABASE -> {
                databaseService.insertArticle(article)
                article.copyDatabaseState(DatabaseState.IN_DATABASE)
            }
            else -> article
        }
    }
}
