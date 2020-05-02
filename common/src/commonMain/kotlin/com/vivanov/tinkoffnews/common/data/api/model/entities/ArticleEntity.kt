package com.vivanov.tinkoffnews.common.data.api.model.entities

import kotlinx.serialization.Serializable

/**
 * @author Vladimir Ivanov
 */
@Serializable
internal class ArticleEntity(
    val id: Long? = null,
    val name: String? = null,
    val text: String? = null,
    val publicationDate: PublicationDateEntity? = null
)
