package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.presentation.states.State
import domain.actions.Action

/**
 * @author Vladimir Ivanov
 */
internal interface Reducer<T : State> {

    fun reduce(state: T, action: Action): T
}
