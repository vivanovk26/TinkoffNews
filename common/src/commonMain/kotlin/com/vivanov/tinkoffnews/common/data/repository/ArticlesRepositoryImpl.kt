package com.vivanov.tinkoffnews.common.data.repository

import com.vivanov.tinkoffnews.common.data.network.mappers.ApiMapper
import com.vivanov.tinkoffnews.common.data.network.services.ApiService
import com.vivanov.tinkoffnews.common.domain.model.Article
import kotlinx.coroutines.delay

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesRepositoryImpl(
    private val apiService: ApiService,
    private val apiMapper: ApiMapper
) : ArticlesRepository {

    private val cachedArticles: MutableList<Article> = mutableListOf()

    override suspend fun getArticles(searchText: String): List<Article> {
        delay(2000L)
        return if (searchText.isBlank()) {
            cachedArticles.clear()
            cachedArticles.addAll(apiMapper.mapArticleResponse(apiService.getArticles()))
            cachedArticles
        } else {
            cachedArticles.filter { article -> article.name.contains(searchText) || article.text.contains(searchText) }
        }
    }
}
