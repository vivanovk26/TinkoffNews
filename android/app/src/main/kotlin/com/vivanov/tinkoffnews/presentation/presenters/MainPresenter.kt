package com.vivanov.tinkoffnews.presentation.presenters

import com.vivanov.tinkoffnews.presentation.states.MainState
import com.vivanov.tinkoffnews.presentation.views.ToolbarSearch
import com.vivanov.tinkoffnews.presentation.views.list.ArticlesListAdapter

/**
 * @author Vladimir Ivanov
 */
internal interface MainPresenter : Presenter<MainState>, ToolbarSearch.ToolbarSearchListener, ArticlesListAdapter.BookmarkClickListener {

    fun refresh()
}
