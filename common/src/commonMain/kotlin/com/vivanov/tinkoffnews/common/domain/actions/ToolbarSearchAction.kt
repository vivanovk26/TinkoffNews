package com.vivanov.tinkoffnews.common.domain.actions

/**
 * @author Vladimir Ivanov
 */
sealed class ToolbarSearchAction : Action {

    class Text(
        val searchText: String
    ) : ToolbarSearchAction()

    class Visible(
        val searchVisible: Boolean
    ) : ToolbarSearchAction()
}
