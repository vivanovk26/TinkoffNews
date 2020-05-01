package com.vivanov.tinkoffnews.common.data.database

import com.vivanov.tinkoffnews.Database

/**
 * @author Vladimir Ivanov
 */
internal actual object SqlDriverFactory {

    actual fun createSqlDriver(): SqlDriver = NativeSqliteDriver(Database.Schema, "articles.db")
}
