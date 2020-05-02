package com.vivanov.tinkoffnews.common.data.repository

import com.vivanov.tinkoffnews.common.domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal interface ArticlesRepository {

    suspend fun getArticlesFromApi(searchText: String): List<Article>

    suspend fun getArticlesFromDb(): List<Article>

    suspend fun updateArticle(article: Article): Article
}
