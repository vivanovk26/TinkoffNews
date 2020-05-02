package com.vivanov.tinkoffnews.common.data.api.mappers

import com.vivanov.tinkoffnews.common.data.api.model.entities.ArticleEntity
import com.vivanov.tinkoffnews.common.data.api.model.responses.ArticlesResponse
import com.vivanov.tinkoffnews.common.data.exceptions.ParseException
import com.vivanov.tinkoffnews.common.domain.model.Article
import com.vivanov.tinkoffnews.common.domain.model.DatabaseState
import kotlin.reflect.KClass

/**
 * @author Vladimir Ivanov
 */
internal class ApiMapperImpl : ApiMapper {

    override fun mapArticleResponse(articlesResponse: ArticlesResponse?, articleDbIds: Set<Long>): List<Article> {
        return articlesResponse?.payload?.let { articleEntities ->
            articleEntities.filter { articleEntity -> isArticleEntityValid(articleEntity) }
                .map { articleEntity -> mapArticleEntity(articleEntity, articleDbIds) }
        } ?: throwParsingException(ArticlesResponse::class)
    }

    private fun isArticleEntityValid(articleEntity: ArticleEntity): Boolean {
        return articleEntity.id != null &&
                !articleEntity.name.isNullOrBlank() &&
                !articleEntity.text.isNullOrBlank() &&
                articleEntity.publicationDate?.milliseconds != null
    }

    private fun mapArticleEntity(articleEntity: ArticleEntity?, articleDbIds: Set<Long>): Article {
        return articleEntity?.let { entity ->
            val id = entity.id.requireNotNull()
            Article(
                id = id,
                name = entity.name.requireNotNullOrNotEmptyString(),
                text = entity.text.requireNotNullOrNotEmptyString(),
                imageUrl = "https://i.picsum.photos/id/${id.rem(1000)}/400/300.jpg", // Add some photo. Because API doesn't provide it.
                publicationDate = entity.publicationDate?.milliseconds.requireNotNull(),
                databaseState = getArticleState(id, articleDbIds)
            )
        } ?: throwParsingException(ArticleEntity::class)
    }

    private fun getArticleState(articleId: Long, articleDbIds: Set<Long>): DatabaseState {
        return when (articleId) {
            in articleDbIds -> DatabaseState.IN_DATABASE
            else -> DatabaseState.OUT_DATABASE
        }
    }

    private fun String?.requireNotNullOrNotEmptyString(): String {
        return require({ !it.isNullOrEmpty() }, { it })
    }

    private inline fun <reified T, R> T?.require(validateFunc: (entity: T?) -> Boolean, mapFunc: (entity: T) -> R): R {
        return if (this != null && validateFunc(this)) {
            mapFunc(this)
        } else {
            throwParsingException(T::class)
        }
    }

    private inline fun <reified T> T?.requireNotNull(): T {
        return require({ it != null }, { it })
    }

    private fun throwParsingException(_class: KClass<*>): Nothing {
        throw ParseException("${_class.simpleName} parsing error")
    }
}
