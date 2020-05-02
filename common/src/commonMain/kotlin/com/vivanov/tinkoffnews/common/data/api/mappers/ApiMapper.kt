package com.vivanov.tinkoffnews.common.data.api.mappers

import com.vivanov.tinkoffnews.common.data.api.model.responses.ArticlesResponse
import com.vivanov.tinkoffnews.common.domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal interface ApiMapper {

    fun mapArticleResponse(articlesResponse: ArticlesResponse?, articleDbIds: Set<Long>): List<Article>
}
