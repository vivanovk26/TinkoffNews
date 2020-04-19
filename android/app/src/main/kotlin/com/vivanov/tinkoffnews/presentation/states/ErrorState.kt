package com.vivanov.tinkoffnews.presentation.states

/**
 * @author Vladimir Ivanov
 */
internal interface ErrorState : State {

    val error: Throwable?
}
