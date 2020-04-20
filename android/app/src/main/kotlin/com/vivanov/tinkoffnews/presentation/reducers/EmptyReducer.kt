package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.R
import com.vivanov.tinkoffnews.data.providers.ResourceProvider
import com.vivanov.tinkoffnews.presentation.actions.ListAction
import com.vivanov.tinkoffnews.presentation.model.EmptyData
import com.vivanov.tinkoffnews.presentation.states.EmptyState
import com.vivanov.tinkoffnews.presentation.states.EmptyStateImpl
import domain.actions.Action

/**
 * @author Vladimir Ivanov
 */
internal class EmptyReducer(
    private val resourceProvider: ResourceProvider
) : Reducer<EmptyState> {

    override fun reduce(state: EmptyState, action: Action): EmptyState {
        return when (action) {
            is ListAction<*> -> {
                if (action.items.isEmpty()) {
                    EmptyStateImpl(
                        EmptyData(
                            iconRes = R.drawable.ic_articles_list_empty_state,
                            title = resourceProvider.getString(R.string.articles_empty_state_title),
                            description = resourceProvider.getString(R.string.articles_empty_state_description)
                        )
                    )
                } else {
                    state
                }
            }
            else -> state
        }
    }
}
