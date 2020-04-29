package com.vivanov.tinkoffnews.presentation.states

/**
 * @author Vladimir Ivanov
 */
internal interface ToolbarSearchState : State {

    val searchText: String
    val searchVisible: Boolean
}
