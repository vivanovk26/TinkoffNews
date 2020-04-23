package com.vivanov.tinkoffnews.common.domain.model

/**
 * @author Vladimir Ivanov
 */
expect class Article constructor(
    id: Int,
    name: String,
    text: String,
    imageUrl: String,
    publicationDate: Long
) {
    val id: Int
    val name: String
    val text: String
    val imageUrl: String
    val publicationDate: Long
}
