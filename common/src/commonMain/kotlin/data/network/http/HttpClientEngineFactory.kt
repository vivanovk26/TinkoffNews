package data.network.http

import io.ktor.client.engine.HttpClientEngine

/**
 * @author Vladimir Ivanov
 */
internal expect object HttpClientEngineFactory {

    fun getHttpClientEngine(): HttpClientEngine
}
