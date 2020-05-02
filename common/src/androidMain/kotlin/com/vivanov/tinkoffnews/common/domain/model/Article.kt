package com.vivanov.tinkoffnews.common.domain.model

import java.io.Serializable

/**
 * @author Vladimir Ivanov
 */
actual data class Article actual constructor(
    actual val id: Long,
    actual val name: String,
    actual val text: String,
    actual val imageUrl: String,
    actual val publicationDate: Long,
    actual val databaseState: DatabaseState
) : Serializable {

    actual fun copyDatabaseState(databaseState: DatabaseState): Article {
        return copy(databaseState = databaseState)
    }
}
