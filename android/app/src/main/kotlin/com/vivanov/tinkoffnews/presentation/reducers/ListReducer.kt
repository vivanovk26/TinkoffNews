package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.presentation.actions.ListAction
import com.vivanov.tinkoffnews.presentation.states.ListState
import com.vivanov.tinkoffnews.presentation.states.ListStateImpl
import domain.actions.Action
import java.io.Serializable

/**
 * @author Vladimir Ivanov
 */
internal abstract class ListReducer<T : Serializable> : Reducer<ListState<T>> {

    override fun reduce(state: ListState<T>, action: Action): ListState<T> {
        if (action is ListAction<*>) {
            return createListState(action.items as List<T>)
        }
        return state
    }

    protected abstract fun createListState(items: List<T>): ListStateImpl<T>
}
