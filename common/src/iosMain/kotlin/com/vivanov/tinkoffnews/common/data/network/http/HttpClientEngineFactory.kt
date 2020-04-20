package com.vivanov.tinkoffnews.common.data.network.http

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios

internal actual object HttpClientEngineFactory {

    actual fun getHttpClientEngine(): HttpClientEngine {
        return Ios.create()
    }
}
