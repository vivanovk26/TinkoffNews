package data.repository

import domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal interface ArticlesRepository {

    suspend fun getArticles(): List<Article>
}
