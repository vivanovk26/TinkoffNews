package com.vivanov.tinkoffnews.common.data.network.model.entities

import kotlinx.serialization.Serializable

/**
 * @author Vladimir Ivanov
 */
@Serializable
internal class ArticleEntity(
    val id: Int? = null,
    val name: String? = null,
    val text: String? = null,
    val publicationDate: PublicationDateEntity? = null
)
