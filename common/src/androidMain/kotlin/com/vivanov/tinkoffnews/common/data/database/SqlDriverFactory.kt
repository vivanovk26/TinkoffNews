package com.vivanov.tinkoffnews.common.data.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

/**
 * @author Vladimir Ivanov
 */
internal actual object SqlDriverFactory {

    // Actually there is Android SqliteDriver but it requires Context.
    actual fun createSqlDriver(): SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
}
