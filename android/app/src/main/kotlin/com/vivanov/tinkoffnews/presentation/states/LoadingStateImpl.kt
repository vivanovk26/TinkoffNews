package com.vivanov.tinkoffnews.presentation.states

/**
 * @author Vladimir Ivanov
 */
internal class LoadingStateImpl(
    override val loadingStateMap: Map<String, Boolean>
) : LoadingState
