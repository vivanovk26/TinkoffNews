package com.vivanov.tinkoffnews.common.domain.actions

/**
 * @author Vladimir Ivanov
 */
sealed class LoadingAction(
    val loading: Boolean
) : Action {

    object Show : LoadingAction(true)

    object Hide : LoadingAction(false)
}
