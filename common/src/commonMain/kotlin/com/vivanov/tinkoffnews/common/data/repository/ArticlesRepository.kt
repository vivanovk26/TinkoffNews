package com.vivanov.tinkoffnews.common.data.repository

import com.vivanov.tinkoffnews.common.domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal interface ArticlesRepository {

    suspend fun getArticles(searchText: String): List<Article>
}
