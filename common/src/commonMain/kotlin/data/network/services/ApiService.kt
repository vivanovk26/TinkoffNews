package data.network.services

import data.network.model.responses.ArticlesResponse

/**
 * @author Vladimir Ivanov
 */
internal interface ApiService {

    suspend fun getArticles(): ArticlesResponse?
}
