package com.vivanov.tinkoffnews.common.data.network.services

import com.vivanov.tinkoffnews.common.data.network.model.responses.ArticlesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * @author Vladimir Ivanov
 */
internal class ApiServiceImpl(
    private val httpClient: HttpClient
) : ApiService {

    override suspend fun getArticles(): ArticlesResponse? {
        return httpClient.get {
            url {
                encodedPath = "news"
            }
        }
    }
}
