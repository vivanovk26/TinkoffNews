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
) : BaseInteractor(), ArticlesListInteractor {

    override fun loadArticles(actionListener: ActionListener) {
        loadArticles(actionListener, LoadingKeys.INITIAL_KEY)
    }

    private fun loadArticles(actionListener: ActionListener, loadingKey: String) {
        flow {
            delay(1000L)
            emit(ListAction(articlesRepository.getArticles()) as Action)
        }
            .onStart {
                emit(LoadingAction.Show(loadingKey))
            }
            .catch { throwable ->
                emit(ErrorAction(throwable))
            }
            .onCompletion {
                emit(LoadingAction.Hide(loadingKey))
            }
            .onEach { action ->
                actionListener.onNextAction(action)
            }
            .launchIn(this)
    }

    override fun refreshArticles(actionListener: ActionListener) {
        loadArticles(actionListener, LoadingKeys.REFRESH_KEY)
    }
}
