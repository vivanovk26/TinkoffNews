package com.vivanov.tinkoffnews.common.data.api.services

import com.vivanov.tinkoffnews.common.domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal interface ApiService {

    suspend fun getArticles(articleDbIds: Set<Long>): List<Article>
}
