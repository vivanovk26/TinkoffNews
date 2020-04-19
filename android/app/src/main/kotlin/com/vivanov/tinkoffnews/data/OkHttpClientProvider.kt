package com.vivanov.tinkoffnews.data

import com.vivanov.tinkoffnews.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * @author Vladimir Ivanov
 */
internal class OkHttpClientProvider {

    private val okHttpClient: OkHttpClient by lazy { createInstance() }

    private fun createInstance(): OkHttpClient {
        return OkHttpClient.Builder().apply {

            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }

            connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)

        }.build()
    }

    fun getInstance(): OkHttpClient {
        return okHttpClient
    }

    private companion object {
        private const val CONNECTION_TIMEOUT: Long = 30L
        private const val READ_WRITE_TIMEOUT: Long = 30L
    }
}

