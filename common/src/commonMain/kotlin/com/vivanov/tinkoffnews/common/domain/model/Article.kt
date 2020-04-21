package com.vivanov.tinkoffnews.common.domain.model

/**
 * @author Vladimir Ivanov
 */
expect class Article(
    id: Int,
    name: String,
    text: String,
    imageUrl: String,
    publicationDate: Long
)
