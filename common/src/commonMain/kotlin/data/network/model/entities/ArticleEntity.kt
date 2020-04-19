package data.network.model.entities

import kotlinx.serialization.Serializable

/**
 * @author Vladimir Ivanov
 */
@Serializable
internal class ArticleEntity(
    val id: String? = null,
    val name: String? = null,
    val text: String? = null,
    val publicationDate: PublicationDateEntity? = null
)
