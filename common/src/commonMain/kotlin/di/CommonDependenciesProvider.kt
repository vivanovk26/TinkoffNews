package di

import domain.interactors.ArticlesListInteractor

/**
 * @author Vladimir Ivanov
 */
object CommonDependenciesProvider {

    fun getArticlesListInteractor(): ArticlesListInteractor {
        return CommonDependenciesResolverImpl.provideArticlesListInteractor()
    }
}
