package com.vivanov.tinkoffnews.common.di

import com.vivanov.tinkoffnews.Database
import com.vivanov.tinkoffnews.common.data.api.http.NewsHttpClientFactory
import com.vivanov.tinkoffnews.common.data.api.mappers.ApiMapper
import com.vivanov.tinkoffnews.common.data.api.mappers.ApiMapperImpl
import com.vivanov.tinkoffnews.common.data.api.services.ApiService
import com.vivanov.tinkoffnews.common.data.api.services.ApiServiceImpl
import com.vivanov.tinkoffnews.common.data.database.SqlDriverFactory
import com.vivanov.tinkoffnews.common.data.database.mappers.DatabaseMapper
import com.vivanov.tinkoffnews.common.data.database.mappers.DatabaseMapperImpl
import com.vivanov.tinkoffnews.common.data.database.services.DatabaseService
import com.vivanov.tinkoffnews.common.data.database.services.DatabaseServiceImpl
import com.vivanov.tinkoffnews.common.data.repository.ArticlesRepository
import com.vivanov.tinkoffnews.common.data.repository.ArticlesRepositoryImpl
import com.vivanov.tinkoffnews.common.domain.interactors.ArticlesListInteractor
import com.vivanov.tinkoffnews.common.domain.interactors.ArticlesListInteractorImpl
import io.ktor.client.HttpClient

/**
 * @author Vladimir Ivanov
 */
internal object CommonDependenciesResolverImpl : CommonDependenciesResolver {

    private val articlesRepository: ArticlesRepository by lazy { ArticlesRepositoryImpl(apiService, databaseService) }
    private val apiService: ApiService by lazy { ApiServiceImpl(httpClient, apiMapper) }
    private val databaseService: DatabaseService by lazy { DatabaseServiceImpl(database, databaseMapper) }
    private val httpClient: HttpClient by lazy { NewsHttpClientFactory.create() }
    private val apiMapper: ApiMapper by lazy { ApiMapperImpl() }
    private val database: Database by lazy { Database(SqlDriverFactory.createSqlDriver()) }
    private val databaseMapper: DatabaseMapper by lazy { DatabaseMapperImpl() }

    override fun provideArticlesListInteractor(): ArticlesListInteractor {
        return ArticlesListInteractorImpl(articlesRepository)
    }
}
