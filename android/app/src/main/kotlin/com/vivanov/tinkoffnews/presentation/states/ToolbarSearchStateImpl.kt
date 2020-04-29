package com.vivanov.tinkoffnews.presentation.states

/**
 * @author Vladimir Ivanov
 */
internal class ToolbarSearchStateImpl(
    override val searchText: String,
    override val searchVisible: Boolean
) : ToolbarSearchState
