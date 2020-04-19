package data.network.mappers

import data.network.model.responses.ArticlesResponse
import domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal interface ApiMapper {

    fun mapArticleResponse(articlesResponse: ArticlesResponse?): List<Article>
}
