package com.vivanov.tinkoffnews.presentation.presenters

import com.vivanov.tinkoffnews.presentation.states.MainState

/**
 * @author Vladimir Ivanov
 */
internal interface MainPresenter : Presenter<MainState> {

    fun refresh()
}
