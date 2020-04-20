package com.vivanov.tinkoffnews.common.data.network.model.responses

import com.vivanov.tinkoffnews.common.data.network.model.entities.ArticleEntity
import kotlinx.serialization.Serializable

/**
 * @author Vladimir Ivanov
 */
@Serializable
internal class ArticlesResponse(
    val resultCode: String? = null,
    val payload: List<ArticleEntity>? = null
)
