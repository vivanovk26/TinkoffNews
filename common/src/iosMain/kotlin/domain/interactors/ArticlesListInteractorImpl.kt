package domain.interactors

import data.repository.ArticlesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesListInteractorImpl(
    private val articlesRepository: ArticlesRepository
) : ArticlesListInteractor, CoroutineScope by MainScope() {

    override fun getArticles(interactorListener: InteractorListener) {
        launch {
            interactorListener.onStart()
            try {
                interactorListener.onSuccess(articlesRepository.getArticles())
            } catch (throwable: Throwable) {
                interactorListener.onError(throwable)
            } finally {
                interactorListener.onCompletion()
            }
        }
    }
}
