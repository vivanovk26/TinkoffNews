package com.vivanov.tinkoffnews.common.data.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.vivanov.tinkoffnews.Database

/**
 * @author Vladimir Ivanov
 */
actual object SqlDriverFactory {

    actual fun createSqlDriver(): SqlDriver = NativeSqliteDriver(Database.Schema, "TinkoffNews.db")
}
