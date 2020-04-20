package domain.interactors

import data.repository.ArticlesRepository
import domain.actions.*
import kotlinx.coroutines.flow.*

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesListInteractorImpl(
    private val articlesRepository: ArticlesRepository
) : ArticlesListInteractor {

    override fun getArticles(actionListener: ActionListener) {
        flow {
            emit(ListAction(articlesRepository.getArticles()) as Action)
        }
            .onStart {
                emit(LoadingAction.Show)
            }
            .catch { throwable ->
                emit(ErrorAction(throwable))
            }
            .onCompletion {
                emit(LoadingAction.Hide)
            }
            .onEach { action ->
                actionListener.onNextAction(action)
            }
            .launchIn(actionListener)
    }
}
