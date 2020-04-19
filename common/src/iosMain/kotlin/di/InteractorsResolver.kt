package di

import domain.interactors.ArticlesListInteractor

/**
 * @author Vladimir Ivanov
 */
interface InteractorsResolver {

    fun provideArticlesListInteractor(): ArticlesListInteractor
}
