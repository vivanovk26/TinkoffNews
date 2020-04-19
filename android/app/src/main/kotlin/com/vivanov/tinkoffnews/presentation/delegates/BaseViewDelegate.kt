package com.vivanov.tinkoffnews.presentation.delegates

import com.vivanov.tinkoffnews.presentation.states.State

/**
 * @author Vladimir Ivanov
 */
internal abstract class BaseViewDelegate<T : State> : ViewDelegate<T> {

    override fun update(state: T) {
        if (shouldUpdate(state)) {
            updateInternal(state)
        }
    }

    protected abstract fun shouldUpdate(state: T): Boolean

    protected abstract fun updateInternal(state: T)
}
