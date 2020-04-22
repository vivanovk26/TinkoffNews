package com.vivanov.tinkoffnews.common.di

import com.squareup.sqldelight.db.SqlDriver
import com.vivanov.tinkoffnews.common.data.network.mappers.ApiMapper
import com.vivanov.tinkoffnews.common.data.network.services.ApiService
import com.vivanov.tinkoffnews.common.data.repository.ArticlesRepository
import com.vivanov.tinkoffnews.common.domain.interactors.ArticlesListInteractor
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

    fun provideSqlDriver(): SqlDriver
}
