package com.vivanov.tinkoffnews.common.domain.model

actual class Article actual constructor(
    actual val id: Long,
    actual val name: String,
    actual val text: String,
    actual val imageUrl: String,
    actual val publicationDate: Long,
    actual val databaseState: DatabaseState
) {

    actual fun copyDatabaseState(databaseState: DatabaseState): Article {
        return Article(
            id = id,
            name = name,
            text = text,
            imageUrl = imageUrl,
            publicationDate = publicationDate,
            databaseState = databaseState
        )
    }
}
