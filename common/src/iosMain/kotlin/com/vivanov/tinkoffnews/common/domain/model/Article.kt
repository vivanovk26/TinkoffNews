package com.vivanov.tinkoffnews.common.domain.model

actual class Article actual constructor(
    actual val id: Long,
    actual val name: String,
    actual val text: String,
    actual val imageUrl: String,
    actual val publicationDate: Long,
    actual var databaseState: DatabaseState
)
