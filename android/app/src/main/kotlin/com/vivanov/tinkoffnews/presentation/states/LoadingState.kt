package com.vivanov.tinkoffnews.presentation.states

/**
 * @author Vladimir Ivanov
 */
internal interface LoadingState : State {

    val loadingStateMap: Map<String, Boolean>
}
