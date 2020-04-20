package com.vivanov.tinkoffnews.common.domain.model

import java.io.Serializable

/**
 * @author Vladimir Ivanov
 */
actual data class Article actual constructor(
    val id: String,
    val name: String,
    val text: String,
    val publicationDate: Long
) : Serializable
