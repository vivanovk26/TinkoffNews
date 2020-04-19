package com.vivanov.tinkoffnews.presentation.presenters

import com.vivanov.tinkoffnews.presentation.actions.Action
import com.vivanov.tinkoffnews.presentation.actions.ErrorAction
import com.vivanov.tinkoffnews.presentation.actions.ListAction
import com.vivanov.tinkoffnews.presentation.actions.LoadingAction
import com.vivanov.tinkoffnews.presentation.reducers.ArticlesListReducer
import com.vivanov.tinkoffnews.presentation.reducers.EmptyReducer
import com.vivanov.tinkoffnews.presentation.reducers.ErrorReducer
import com.vivanov.tinkoffnews.presentation.reducers.LoadingReducer
import com.vivanov.tinkoffnews.presentation.states.MainState
import domain.interactors.ArticlesListInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * @author Vladimir Ivanov
 */
internal class MainPresenter(
    private val articlesListInteractor: ArticlesListInteractor,
    private val emptyReducer: EmptyReducer
) : BasePresenter<MainState>() {

    override fun createState(): MainState = MainState()

    override fun onPresenterCreated() {
        super.onPresenterCreated()

        articlesListInteractor.getArticles()
            .flowOn(Dispatchers.IO)
            .flatMapConcat { articles ->
                flowOf(ListAction(articles) as Action)
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
                val state = stateLiveData.value!! // TODO v.ivanov fix
                stateLiveData.value = state.copy(
                    listState = ArticlesListReducer.reduce(state.listState, action),
                    loadingState = LoadingReducer.reduce(state.loadingState, action),
                    emptyState = emptyReducer.reduce(state, action),
                    errorState = ErrorReducer.reduce(state.errorState, action)
                )
            }
            .launchIn(this)
    }
}
