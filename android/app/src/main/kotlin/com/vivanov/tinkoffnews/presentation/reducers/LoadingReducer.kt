package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.common.domain.actions.Action
import com.vivanov.tinkoffnews.common.domain.actions.LoadingAction
import com.vivanov.tinkoffnews.presentation.states.LoadingState
import com.vivanov.tinkoffnews.presentation.states.LoadingStateImpl

/**
 * @author Vladimir Ivanov
 */
internal object LoadingReducer : Reducer<LoadingState> {

    override fun reduce(state: LoadingState, action: Action): LoadingState {
        if (action is LoadingAction) {
            return LoadingStateImpl(action.loading)
        }
        return state
    }
}
