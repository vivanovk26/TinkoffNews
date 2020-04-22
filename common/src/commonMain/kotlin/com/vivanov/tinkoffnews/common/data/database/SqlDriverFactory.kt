package com.vivanov.tinkoffnews.common.data.database

import com.squareup.sqldelight.db.SqlDriver

/**
 * @author Vladimir Ivanov
 */
internal expect object SqlDriverFactory {

    fun createSqlDriver(): SqlDriver
}
