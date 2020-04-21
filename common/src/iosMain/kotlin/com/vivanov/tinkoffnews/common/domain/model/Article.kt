package com.vivanov.tinkoffnews.common.domain.model

actual class Article actual constructor(
    val id: Int,
    val name: String,
    val text: String,
    val imageUrl: String,
    val publicationDate: Long
)
