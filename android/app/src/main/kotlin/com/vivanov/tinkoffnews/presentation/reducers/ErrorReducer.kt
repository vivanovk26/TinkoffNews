package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.common.domain.actions.Action
import com.vivanov.tinkoffnews.common.domain.actions.ErrorAction
import com.vivanov.tinkoffnews.common.domain.actions.LoadingAction
import com.vivanov.tinkoffnews.presentation.states.ErrorState
import com.vivanov.tinkoffnews.presentation.states.ErrorStateImpl

/**
 * @author Vladimir Ivanov
 */
internal object ErrorReducer : Reducer<ErrorState> {

    override fun reduce(state: ErrorState, action: Action): ErrorState {
        return when (action) {
            is ErrorAction -> ErrorStateImpl(action.throwable)
            is LoadingAction -> ErrorStateImpl(null)
            else -> state
        }
    }
}
