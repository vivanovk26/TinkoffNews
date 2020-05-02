package com.vivanov.tinkoffnews.common.data.api.http

import io.ktor.client.engine.HttpClientEngine

/**
 * @author Vladimir Ivanov
 */
internal expect object HttpClientEngineFactory {

    fun getHttpClientEngine(): HttpClientEngine
}
