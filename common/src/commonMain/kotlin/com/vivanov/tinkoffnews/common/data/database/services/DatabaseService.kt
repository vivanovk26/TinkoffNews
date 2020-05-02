package com.vivanov.tinkoffnews.common.data.database.services

import com.vivanov.tinkoffnews.common.domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal interface DatabaseService {

    suspend fun getAllArticleIds(): Set<Long>

    suspend fun getAllArticles(): List<Article>

    suspend fun insertArticle(article: Article)

    suspend fun deleteArticle(id: Long)
}
