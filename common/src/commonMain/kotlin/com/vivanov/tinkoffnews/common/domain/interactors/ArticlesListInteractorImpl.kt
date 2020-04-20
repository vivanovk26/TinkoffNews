package com.vivanov.tinkoffnews.common.domain.interactors

import com.vivanov.tinkoffnews.common.data.repository.ArticlesRepository
import com.vivanov.tinkoffnews.common.domain.actions.*
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
