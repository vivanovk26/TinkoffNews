package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.common.domain.actions.Action
import com.vivanov.tinkoffnews.presentation.states.State

/**
 * @author Vladimir Ivanov
 */
internal interface Reducer<T : State> {

    fun reduce(state: T, action: Action): T
}
