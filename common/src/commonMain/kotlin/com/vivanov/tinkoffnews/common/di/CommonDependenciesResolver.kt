package com.vivanov.tinkoffnews.common.di

import com.vivanov.tinkoffnews.common.domain.interactors.ArticlesListInteractor

/**
 * @author Vladimir Ivanov
 */
internal interface CommonDependenciesResolver {

    fun provideArticlesListInteractor(): ArticlesListInteractor
}
