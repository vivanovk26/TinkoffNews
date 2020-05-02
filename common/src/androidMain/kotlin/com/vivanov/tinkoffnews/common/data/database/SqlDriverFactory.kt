package com.vivanov.tinkoffnews.common.data.database

import com.squareup.sqldelight.db.SqlDriver

/**
 * @author Vladimir Ivanov
 */
actual object SqlDriverFactory {

    private var sqlDriver: SqlDriver? = null

    actual fun createSqlDriver(): SqlDriver {
        // Because we already set it from setSqlDriver
        return sqlDriver!!
    }

    /**
     * This is a special method for Android. Because AndroidSqlDriver requires context.
     */
    fun setupSqlDriver(sqlDriver: SqlDriver) {
        this.sqlDriver = sqlDriver
    }
}
