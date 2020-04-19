package com.vivanov.tinkoffnews.presentation.actions

/**
 * @author Vladimir Ivanov
 */
internal sealed class LoadingAction(
    val loading: Boolean
) : Action {

    object Show : LoadingAction(true)

    object Hide : LoadingAction(false)
}
