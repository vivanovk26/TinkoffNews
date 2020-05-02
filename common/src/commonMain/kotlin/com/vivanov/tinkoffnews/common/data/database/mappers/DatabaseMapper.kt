package com.vivanov.tinkoffnews.common.data.database.mappers

import com.vivanov.tinkoffnews.ArticleDb
import com.vivanov.tinkoffnews.common.domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal interface DatabaseMapper {

    fun mapArticlesFromDb(articlesDb: List<ArticleDb>): List<Article>

    fun mapArticleToDb(article: Article): ArticleDb
}
