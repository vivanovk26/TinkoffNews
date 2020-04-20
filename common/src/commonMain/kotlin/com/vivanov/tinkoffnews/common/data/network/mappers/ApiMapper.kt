package com.vivanov.tinkoffnews.common.data.network.mappers

import com.vivanov.tinkoffnews.common.data.network.model.responses.ArticlesResponse
import com.vivanov.tinkoffnews.common.domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal interface ApiMapper {

    fun mapArticleResponse(articlesResponse: ArticlesResponse?): List<Article>
}
