package com.vivanov.tinkoffnews.presentation.presenters

import com.vivanov.tinkoffnews.common.domain.actions.Action
import com.vivanov.tinkoffnews.common.domain.actions.ActionListener
import com.vivanov.tinkoffnews.common.domain.actions.ToolbarSearchAction
import com.vivanov.tinkoffnews.common.domain.interactors.ArticlesListInteractor
import com.vivanov.tinkoffnews.presentation.reducers.*
import com.vivanov.tinkoffnews.presentation.states.MainState

/**
 * @author Vladimir Ivanov
 */
internal class MainPresenterImpl(
    private val articlesListInteractor: ArticlesListInteractor,
    private val emptyReducer: EmptyReducer
) : BasePresenter<MainState>(), MainPresenter, ActionListener {

    override fun createState(): MainState = MainState()

    override fun onPresenterCreated() {
        super.onPresenterCreated()

        articlesListInteractor.loadArticles(this)
    }

    override fun refresh() {
        articlesListInteractor.refreshArticles(this)
    }

    override fun onNextAction(action: Action) {
        val state = stateLiveData.value!! // TODO not a good idea.
        stateLiveData.value = state.copy(
            toolbarSearchState = ToolbarSearchReducer.reduce(state.toolbarSearchState, action),
            listState = ArticlesListReducer.reduce(state.listState, action),
            loadingState = LoadingReducer.reduce(state.loadingState, action),
            emptyState = emptyReducer.reduce(state, action),
            errorState = ErrorReducer.reduce(state.errorState, action)
        )
    }

    override fun onSearchTextChanged(text: String) {
        onNextAction(ToolbarSearchAction.Text(text))

        articlesListInteractor.searchArticles(this, text)
    }

    override fun onSearchVisibilityChanged(visible: Boolean) {
        onNextAction(ToolbarSearchAction.Visible(visible))
    }

    override fun onDestroy() {
        articlesListInteractor.onDestroy() // TODO think again later.

        super.onDestroy()
    }
}
