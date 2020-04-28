package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.R
import com.vivanov.tinkoffnews.common.domain.actions.Action
import com.vivanov.tinkoffnews.common.domain.actions.ListAction
import com.vivanov.tinkoffnews.data.providers.ResourceProvider
import com.vivanov.tinkoffnews.presentation.model.EmptyData
import com.vivanov.tinkoffnews.presentation.states.EmptyState
import com.vivanov.tinkoffnews.presentation.states.EmptyStateImpl

/**
 * @author Vladimir Ivanov
 */
internal class EmptyReducer(
    private val resourceProvider: ResourceProvider
) : Reducer<EmptyState> {

    override fun reduce(state: EmptyState, action: Action): EmptyState {
        return when {
            action is ListAction<*> && action.items.isEmpty() -> {
                val searchText = action.searchText
                EmptyStateImpl(
                    emptyData = if (searchText.isEmpty()) {
                        EmptyData(
                            iconRes = R.drawable.ic_articles_list_empty_state,
                            title = resourceProvider.getString(R.string.articles_empty_state_title),
                            description = resourceProvider.getString(R.string.articles_empty_state_description)
                        )
                    } else {
                        EmptyData(
                            iconRes = R.drawable.ic_search_empty_state,
                            title = resourceProvider.getString(R.string.search_empty_state_title, searchText),
                            description = resourceProvider.getString(R.string.search_empty_state_description)
                        )
                    }
                )
            }
            action is ListAction<*> && action.items.isNotEmpty() -> {
                EmptyStateImpl(null)
            }
            else -> {
                state
            }
        }
    }
}
