package com.vivanov.tinkoffnews.common.data.network.http

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.host
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * @author Vladimir Ivanov
 */
internal object NewsHttpClientFactory {

    fun create(): HttpClient {
        return HttpClient(HttpClientEngineFactory.getHttpClientEngine()) {
            expectSuccess = false
            defaultRequest {
                url.protocol = URLProtocol.HTTPS
                host = "api.tinkoff.ru/v1"
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 60_000L
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json(
                        JsonConfiguration(
                            isLenient = true,
                            ignoreUnknownKeys = true,
                            serializeSpecialFloatingPointValues = true,
                            useArrayPolymorphism = true
                        )
                    )
                )
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }
}
