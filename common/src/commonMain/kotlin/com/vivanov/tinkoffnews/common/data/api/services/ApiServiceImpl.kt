package com.vivanov.tinkoffnews.common.data.api.services

import com.vivanov.tinkoffnews.common.data.api.mappers.ApiMapper
import com.vivanov.tinkoffnews.common.domain.model.Article
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.delay

/**
 * @author Vladimir Ivanov
 */
internal class ApiServiceImpl(
    private val httpClient: HttpClient,
    private val apiMapper: ApiMapper
) : ApiService {

    override suspend fun getArticles(): List<Article> {
        delay(2000L)
        return apiMapper.mapArticleResponse(
            httpClient.get {
                url {
                    encodedPath = "news"
                }
            }
        )
    }
}
