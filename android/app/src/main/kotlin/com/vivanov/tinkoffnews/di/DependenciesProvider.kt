package com.vivanov.tinkoffnews.di

import android.content.Context
import com.vivanov.tinkoffnews.data.providers.ResourceProvider
import com.vivanov.tinkoffnews.presentation.reducers.EmptyReducer

/**
 * @author Vladimir Ivanov
 */
internal interface DependenciesProvider {

    fun setup(context: Context)

    fun provideEmptyReducer(): EmptyReducer

    fun provideResourceProvider(): ResourceProvider
}
