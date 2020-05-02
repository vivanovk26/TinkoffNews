package com.vivanov.tinkoffnews.common.data.api.http

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

/**
 * @author Vladimir Ivanov
 */
internal actual object HttpClientEngineFactory {

    actual fun getHttpClientEngine(): HttpClientEngine {
        return OkHttp.create()
    }
}
