package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.common.domain.actions.Action
import com.vivanov.tinkoffnews.common.domain.actions.ToolbarSearchAction
import com.vivanov.tinkoffnews.presentation.states.ToolbarSearchState
import com.vivanov.tinkoffnews.presentation.states.ToolbarSearchStateImpl

/**
 * @author Vladimir Ivanov
 */
internal object ToolbarSearchReducer : Reducer<ToolbarSearchState> {

    override fun reduce(state: ToolbarSearchState, action: Action): ToolbarSearchState {
        return when (action) {
            is ToolbarSearchAction.Text -> ToolbarSearchStateImpl(
                searchText = action.searchText,
                searchVisible = state.searchVisible
            )
            is ToolbarSearchAction.Visible -> ToolbarSearchStateImpl(
                searchText = state.searchText,
                searchVisible = action.searchVisible
            )
            else -> state
        }
    }
}
