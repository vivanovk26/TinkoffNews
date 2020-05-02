package com.vivanov.tinkoffnews.common.data.api.model.responses

import com.vivanov.tinkoffnews.common.data.api.model.entities.ArticleEntity
import kotlinx.serialization.Serializable

/**
 * @author Vladimir Ivanov
 */
@Serializable
internal class ArticlesResponse(
    val resultCode: String? = null,
    val payload: List<ArticleEntity>? = null
)
