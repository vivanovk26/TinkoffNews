package com.vivanov.tinkoffnews.common.domain.model

import java.io.Serializable

/**
 * @author Vladimir Ivanov
 */
actual data class Article actual constructor(
    val id: Int,
    val name: String,
    val text: String,
    val imageUrl: String,
    val publicationDate: Long
) : Serializable
