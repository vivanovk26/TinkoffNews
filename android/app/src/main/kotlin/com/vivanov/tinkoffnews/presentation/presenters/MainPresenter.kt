package com.vivanov.tinkoffnews.presentation.presenters

import com.vivanov.tinkoffnews.presentation.states.MainState
import com.vivanov.tinkoffnews.presentation.views.ToolbarSearch

/**
 * @author Vladimir Ivanov
 */
internal interface MainPresenter : Presenter<MainState>, ToolbarSearch.ToolbarSearchListener {

    fun refresh()
}
