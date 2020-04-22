package com.vivanov.tinkoffnews.common.data.database

import com.squareup.sqldelight.db.SqlDriver

/**
 * @author Vladimir Ivanov
 */
internal actual object SqlDriverFactory {

    actual fun createSqlDriver(): SqlDriver =
        AndroidJdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY) // Actually there is Android SqliteDriver but it requires Context.
}
