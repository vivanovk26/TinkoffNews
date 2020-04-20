package di

import data.network.mappers.ApiMapper
import data.network.services.ApiService
import data.repository.ArticlesRepository
import domain.interactors.ArticlesListInteractor
import io.ktor.client.HttpClient

/**
 * @author Vladimir Ivanov
 */
internal interface CommonDependenciesResolver {

    fun provideArticlesListInteractor(): ArticlesListInteractor

    fun provideArticlesRepository(): ArticlesRepository

    fun provideApiService(): ApiService

    fun provideHttpClient(): HttpClient

    fun provideApiMapper(): ApiMapper
}
