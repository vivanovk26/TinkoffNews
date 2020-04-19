package di

import domain.interactors.ArticlesListInteractor
import domain.interactors.ArticlesListInteractorImpl

/**
 * @author Vladimir Ivanov
 */
internal class InteractorsResolverImpl(
    private val commonDependenciesResolver: CommonDependenciesResolver
) : InteractorsResolver {

    private val articlesListInteractor: ArticlesListInteractor by lazy {
        ArticlesListInteractorImpl(
            articlesRepository = commonDependenciesResolver.provideArticlesRepository()
        )
    }

    override fun provideArticlesListInteractor(): ArticlesListInteractor {
        return articlesListInteractor
    }
}
