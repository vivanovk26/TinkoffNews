package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.common.domain.actions.Action
import com.vivanov.tinkoffnews.common.domain.actions.ListSearchAction
import com.vivanov.tinkoffnews.presentation.states.ListState
import com.vivanov.tinkoffnews.presentation.states.ListStateImpl
import java.io.Serializable

/**
 * @author Vladimir Ivanov
 */
internal abstract class ListReducer<T : Serializable> : Reducer<ListState<T>> {

    override fun reduce(state: ListState<T>, action: Action): ListState<T> {
        if (action is ListSearchAction<*>) {
            return createListState(action.items as List<T>)
        }
        return state
    }

    protected abstract fun createListState(items: List<T>): ListStateImpl<T>
}
