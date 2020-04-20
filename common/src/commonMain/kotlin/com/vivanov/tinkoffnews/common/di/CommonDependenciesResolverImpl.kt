package com.vivanov.tinkoffnews.common.di

import com.vivanov.tinkoffnews.common.data.network.http.NewsHttpClientFactory
import com.vivanov.tinkoffnews.common.data.network.mappers.ApiMapper
import com.vivanov.tinkoffnews.common.data.network.mappers.ApiMapperImpl
import com.vivanov.tinkoffnews.common.data.network.services.ApiService
import com.vivanov.tinkoffnews.common.data.network.services.ApiServiceImpl
import com.vivanov.tinkoffnews.common.data.repository.ArticlesRepository
import com.vivanov.tinkoffnews.common.data.repository.ArticlesRepositoryImpl
import com.vivanov.tinkoffnews.common.domain.interactors.ArticlesListInteractor
import com.vivanov.tinkoffnews.common.domain.interactors.ArticlesListInteractorImpl
import io.ktor.client.HttpClient

/**
 * @author Vladimir Ivanov
 */
internal object CommonDependenciesResolverImpl : CommonDependenciesResolver {

    private val articlesRepository: ArticlesRepository by lazy { ArticlesRepositoryImpl(apiService, apiMapper) }
    private val apiService: ApiService by lazy { ApiServiceImpl(httpClient) }
    private val httpClient: HttpClient by lazy { NewsHttpClientFactory.create() }
    private val apiMapper: ApiMapper by lazy { ApiMapperImpl() }

    override fun provideArticlesListInteractor(): ArticlesListInteractor {
        return ArticlesListInteractorImpl(articlesRepository)
    }

    override fun provideArticlesRepository(): ArticlesRepository {
        return articlesRepository
    }

    override fun provideApiService(): ApiService {
        return apiService
    }

    override fun provideHttpClient(): HttpClient {
        return httpClient
    }

    override fun provideApiMapper(): ApiMapper {
        return apiMapper
    }
}
