package com.vivanov.tinkoffnews.presentation.states

import com.vivanov.tinkoffnews.presentation.model.EmptyData

/**
 * @author Vladimir Ivanov
 */
internal interface EmptyState : State {

    val emptyData: EmptyData?
}
