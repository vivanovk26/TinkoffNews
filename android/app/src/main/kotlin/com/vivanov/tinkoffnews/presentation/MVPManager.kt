package com.vivanov.tinkoffnews.presentation

import com.vivanov.tinkoffnews.common.di.CommonDependenciesProvider
import com.vivanov.tinkoffnews.di.DependenciesProvider
import com.vivanov.tinkoffnews.di.DependenciesProviderImpl
import com.vivanov.tinkoffnews.presentation.presenters.MainPresenter
import com.vivanov.tinkoffnews.presentation.presenters.Presenter
import kotlin.reflect.KClass

/**
 * @author Vladimir Ivanov
 */
internal object MVPManager {

    private val commonDependenciesProvider: CommonDependenciesProvider by lazy { CommonDependenciesProvider }
    private val dependenciesProvider: DependenciesProvider by lazy { DependenciesProviderImpl }

    private val map: MutableMap<KClass<*>, Presenter<*>> = mutableMapOf()

    inline fun <reified T : Presenter<*>> getPresenter(_class: KClass<T>): T {
        if (!map.containsKey(_class)) {
            map[_class] = createPresenter(_class)
        }
        return requireNotNull(map[_class]) as T
    }

    private inline fun <reified T : Presenter<*>> createPresenter(_class: KClass<T>): T {
        return when (_class) {
            MainPresenter::class -> MainPresenter(
                articlesListInteractor = commonDependenciesProvider.getArticlesListInteractor(),
                emptyReducer = dependenciesProvider.provideEmptyReducer()
            )
            else -> throw IllegalStateException("No presenter found for this type")
        } as T
    }

    fun removePresenter(presenter: Presenter<*>) {
        map.remove(presenter::class)
    }
}
