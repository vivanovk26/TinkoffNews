package com.vivanov.tinkoffnews.common.domain.model

import java.io.Serializable

/**
 * @author Vladimir Ivanov
 */
actual data class Article actual constructor(
    actual val id: Int,
    actual val name: String,
    actual val text: String,
    actual val imageUrl: String,
    actual val publicationDate: Long
) : Serializable
