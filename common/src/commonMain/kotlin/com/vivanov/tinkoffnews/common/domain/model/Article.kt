package com.vivanov.tinkoffnews.common.domain.model

/**
 * @author Vladimir Ivanov
 */
expect class Article constructor(
    id: Long,
    name: String,
    text: String,
    imageUrl: String,
    publicationDate: Long,
    databaseState: DatabaseState
) {
    val id: Long
    val name: String
    val text: String
    val imageUrl: String
    val publicationDate: Long
    val databaseState: DatabaseState

    fun copyDatabaseState(databaseState: DatabaseState): Article
}
