package di

import data.network.mappers.ApiMapper
import data.network.services.ApiService
import data.repository.ArticlesRepository
import io.ktor.client.HttpClient

/**
 * @author Vladimir Ivanov
 */
internal interface CommonDependenciesResolver {

    fun provideArticlesRepository(): ArticlesRepository

    fun provideApiService(): ApiService

    fun provideHttpClient(): HttpClient

    fun provideApiMapper(): ApiMapper
}
