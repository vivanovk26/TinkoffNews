package com.vivanov.tinkoffnews.common.data.database.services

import com.vivanov.tinkoffnews.ArticleDbQueries
import com.vivanov.tinkoffnews.Database
import com.vivanov.tinkoffnews.common.data.database.mappers.DatabaseMapper
import com.vivanov.tinkoffnews.common.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Vladimir Ivanov
 */
internal class DatabaseServiceImpl(
    database: Database,
    private val databaseMapper: DatabaseMapper
) : DatabaseService {

    private val articleDbQueries: ArticleDbQueries = database.articleDbQueries
    private val articleDbIds: MutableList<Long> = mutableListOf()
    private var articleDbIdsLoaded: Boolean = false

    override suspend fun getAllArticleIds(): List<Long> {
        return articleDbIds.apply {
            if (!articleDbIdsLoaded) {
                withContext(Dispatchers.Default) {
                    addAll(articleDbQueries.getAllIds().executeAsList())
                }
                articleDbIdsLoaded = true
            }
        }
    }

    override suspend fun getAllArticles(): List<Article> {
        return withContext(Dispatchers.Default) {
            databaseMapper.mapArticlesFromDb(articleDbQueries.getAll().executeAsList())
        }
    }

    override suspend fun insertArticle(article: Article) {
        return withContext(Dispatchers.Default) {
            articleDbQueries.insert(databaseMapper.mapArticleToDb(article))
        }
    }

    override suspend fun deleteArticle(id: Long) {
        return withContext(Dispatchers.Default) {
            articleDbQueries.delete(id)
        }
    }
}
