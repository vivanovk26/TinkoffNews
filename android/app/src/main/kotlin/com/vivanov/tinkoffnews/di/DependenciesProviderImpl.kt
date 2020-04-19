package com.vivanov.tinkoffnews.di

import android.content.Context
import com.vivanov.tinkoffnews.data.providers.ResourceProvider
import com.vivanov.tinkoffnews.data.providers.ResourceProviderImpl
import com.vivanov.tinkoffnews.presentation.reducers.EmptyReducer

/**
 * @author Vladimir Ivanov
 */
internal object DependenciesProviderImpl : DependenciesProvider {

    private lateinit var context: Context
    private val emptyReducer: EmptyReducer by lazy { EmptyReducer(resourceProvider) }
    private val resourceProvider: ResourceProvider by lazy { ResourceProviderImpl(context) }

    override fun setup(context: Context) {
        this.context = context
    }

    override fun provideEmptyReducer(): EmptyReducer {
        return emptyReducer
    }

    override fun provideResourceProvider(): ResourceProvider {
        return resourceProvider
    }
}
