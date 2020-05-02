package com.vivanov.tinkoffnews.common.data.database.mappers

import com.vivanov.tinkoffnews.ArticleDb
import com.vivanov.tinkoffnews.common.domain.model.Article
import com.vivanov.tinkoffnews.common.domain.model.DatabaseState

/**
 * @author Vladimir Ivanov
 */
internal class DatabaseMapperImpl : DatabaseMapper {

    override fun mapArticlesFromDb(articlesDb: List<ArticleDb>): List<Article> {
        return articlesDb.map { articleDb ->
            with(articleDb) {
                Article(
                    id = id,
                    name = name,
                    text = text,
                    imageUrl = imageUrl,
                    publicationDate = publicationDate,
                    databaseState = DatabaseState.IN_DATABASE
                )
            }
        }
    }

    override fun mapArticleToDb(article: Article): ArticleDb {
        with(article) {
            return ArticleDb.Impl(
                id = id,
                name = name,
                text = text,
                imageUrl = imageUrl,
                publicationDate = publicationDate
            )
        }
    }
}
