package com.vivanov.tinkoffnews.common.domain.interactors

import com.vivanov.tinkoffnews.common.data.repository.ArticlesRepository
import com.vivanov.tinkoffnews.common.domain.actions.*
import com.vivanov.tinkoffnews.common.presentation.LoadingKeys
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesListInteractorImpl(
    private val articlesRepository: ArticlesRepository
) : ArticlesListInteractor {

    override fun getArticles(actionListener: ActionListener) {
        flow {
            delay(2000L)
            emit(ListAction(articlesRepository.getArticles()) as Action)
        }
            .onStart {
                emit(LoadingAction.Show(LoadingKeys.INITIAL_KEY))
            }
            .catch { throwable ->
                emit(ErrorAction(throwable))
            }
            .onCompletion {
                emit(LoadingAction.Hide(LoadingKeys.INITIAL_KEY))
            }
            .onEach { action ->
                actionListener.onNextAction(action)
            }
            .launchIn(actionListener)
    }
}
