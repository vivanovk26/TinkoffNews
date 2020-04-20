package com.vivanov.tinkoffnews.presentation.presenters

import com.vivanov.tinkoffnews.presentation.reducers.ArticlesListReducer
import com.vivanov.tinkoffnews.presentation.reducers.EmptyReducer
import com.vivanov.tinkoffnews.presentation.reducers.ErrorReducer
import com.vivanov.tinkoffnews.presentation.reducers.LoadingReducer
import com.vivanov.tinkoffnews.presentation.states.MainState
import domain.actions.Action
import domain.actions.ActionListener
import domain.interactors.ArticlesListInteractor

/**
 * @author Vladimir Ivanov
 */
internal class MainPresenter(
    private val articlesListInteractor: ArticlesListInteractor,
    private val emptyReducer: EmptyReducer
) : BasePresenter<MainState>(), ActionListener {

    override fun createState(): MainState = MainState()

    override fun onPresenterCreated() {
        super.onPresenterCreated()

        articlesListInteractor.getArticles(this)
    }

    override fun onNextAction(action: Action) {
        val state = stateLiveData.value!! // TODO v.ivanov fix
        stateLiveData.value = state.copy(
            listState = ArticlesListReducer.reduce(state.listState, action),
            loadingState = LoadingReducer.reduce(state.loadingState, action),
            emptyState = emptyReducer.reduce(state, action),
            errorState = ErrorReducer.reduce(state.errorState, action)
        )
    }
}
