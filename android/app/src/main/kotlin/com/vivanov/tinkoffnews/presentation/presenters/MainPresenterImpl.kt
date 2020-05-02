package com.vivanov.tinkoffnews.presentation.presenters

import com.vivanov.tinkoffnews.common.domain.actions.Action
import com.vivanov.tinkoffnews.common.domain.actions.ActionListener
import com.vivanov.tinkoffnews.common.domain.actions.ToolbarSearchAction
import com.vivanov.tinkoffnews.common.domain.interactors.ArticlesListInteractor
import com.vivanov.tinkoffnews.common.domain.model.Article
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
        articlesListInteractor.searchArticles(this, state.searchText)
    }

    override fun onNextAction(action: Action) {
        val currentState = state
        stateLiveData.value = currentState.copy(
            toolbarSearchState = ToolbarSearchReducer.reduce(currentState.toolbarSearchState, action),
            listState = ArticlesListReducer.reduce(currentState.listState, action),
            loadingState = LoadingReducer.reduce(currentState.loadingState, action),
            emptyState = emptyReducer.reduce(currentState, action),
            errorState = ErrorReducer.reduce(currentState.errorState, action)
        )
    }

    override fun onSearchTextChanged(text: String) {
        onNextAction(ToolbarSearchAction.Text(text))

        articlesListInteractor.searchArticles(this, text)
    }

    override fun onSearchVisibilityChanged(visible: Boolean) {
        onNextAction(ToolbarSearchAction.Visible(visible))
    }

    override fun onBookmarkClick(article: Article) {
        articlesListInteractor.updateBookmark(this, article)
    }

    override fun onDestroy() {
        articlesListInteractor.onDestroy() // TODO think again about unify for every interactor.

        super.onDestroy()
    }
}
