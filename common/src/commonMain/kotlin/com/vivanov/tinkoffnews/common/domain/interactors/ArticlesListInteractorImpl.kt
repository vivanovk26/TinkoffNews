package com.vivanov.tinkoffnews.common.domain.interactors

import com.vivanov.tinkoffnews.common.data.repository.ArticlesRepository
import com.vivanov.tinkoffnews.common.domain.actions.*
import com.vivanov.tinkoffnews.common.presentation.LoadingKeys
import kotlinx.coroutines.flow.*

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesListInteractorImpl(
    private val articlesRepository: ArticlesRepository
) : BaseInteractor(), ArticlesListInteractor {

    override fun loadArticles(actionListener: ActionListener) {
        loadArticles(
            actionListener = actionListener,
            searchText = "",
            loadingKey = LoadingKeys.INITIAL_KEY
        )
    }

    private fun loadArticles(
        actionListener: ActionListener,
        searchText: String,
        loadingKey: String
    ) {
        flow {
            emit(ListSearchAction(articlesRepository.getArticles(searchText), searchText) as Action)
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

    override fun searchArticles(actionListener: ActionListener, searchText: String) {
        loadArticles(
            actionListener = actionListener,
            searchText = searchText,
            loadingKey = LoadingKeys.REFRESH_KEY
        )
    }
}
