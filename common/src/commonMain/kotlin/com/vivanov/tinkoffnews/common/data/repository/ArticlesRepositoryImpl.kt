package com.vivanov.tinkoffnews.common.data.repository

import com.vivanov.tinkoffnews.common.data.network.mappers.ApiMapper
import com.vivanov.tinkoffnews.common.data.network.services.ApiService
import com.vivanov.tinkoffnews.common.domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesRepositoryImpl(
    private val apiService: ApiService,
    private val apiMapper: ApiMapper
) : ArticlesRepository {

    override suspend fun getArticles(): List<Article> {
        return apiMapper.mapArticleResponse(apiService.getArticles())
    }
}
