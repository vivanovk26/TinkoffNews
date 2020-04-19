package data.network.mappers

import data.exceptions.ParseException
import data.network.model.entities.ArticleEntity
import data.network.model.responses.ArticlesResponse
import domain.model.Article
import kotlin.reflect.KClass

/**
 * @author Vladimir Ivanov
 */
internal class ApiMapperImpl : ApiMapper {

    override fun mapArticleResponse(articlesResponse: ArticlesResponse?): List<Article> {
        return articlesResponse?.payload?.let { articleEntities ->
            articleEntities.filter { articleEntity -> isArticleEntityValid(articleEntity) }
                .map { articleEntity -> mapArticleEntity(articleEntity) }
        } ?: throwParsingException(ArticlesResponse::class)
    }

    private fun isArticleEntityValid(articleEntity: ArticleEntity): Boolean {
        return !articleEntity.id.isNullOrBlank() &&
                !articleEntity.name.isNullOrBlank() &&
                !articleEntity.text.isNullOrBlank() &&
                articleEntity.publicationDate?.milliseconds != null
    }

    private fun mapArticleEntity(articleEntity: ArticleEntity?): Article {
        return articleEntity?.let { entity ->
            Article(
                id = entity.id.requireNotNullOrNotEmptyString(),
                name = entity.name.requireNotNullOrNotEmptyString(),
                text = entity.text.requireNotNullOrNotEmptyString(),
                publicationDate = entity.publicationDate?.milliseconds.requireNotNull()
            )
        } ?: throwParsingException(ArticleEntity::class)
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
