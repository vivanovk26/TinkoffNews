package di

import data.network.http.NewsHttpClientFactory
import data.network.mappers.ApiMapper
import data.network.mappers.ApiMapperImpl
import data.network.services.ApiService
import data.network.services.ApiServiceImpl
import data.repository.ArticlesRepository
import data.repository.ArticlesRepositoryImpl
import domain.interactors.ArticlesListInteractor
import domain.interactors.ArticlesListInteractorImpl
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
