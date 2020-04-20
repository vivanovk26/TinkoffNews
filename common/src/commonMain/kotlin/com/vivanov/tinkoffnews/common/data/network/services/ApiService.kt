package com.vivanov.tinkoffnews.common.data.network.services

import com.vivanov.tinkoffnews.common.data.network.model.responses.ArticlesResponse

/**
 * @author Vladimir Ivanov
 */
internal interface ApiService {

    suspend fun getArticles(): ArticlesResponse?
}
