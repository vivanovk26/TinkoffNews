package com.vivanov.tinkoffnews.common.di

import com.vivanov.tinkoffnews.common.domain.interactors.ArticlesListInteractor

/**
 * @author Vladimir Ivanov
 */
object CommonDependenciesProvider {

    fun getArticlesListInteractor(): ArticlesListInteractor {
        return CommonDependenciesResolverImpl.provideArticlesListInteractor()
    }
}
