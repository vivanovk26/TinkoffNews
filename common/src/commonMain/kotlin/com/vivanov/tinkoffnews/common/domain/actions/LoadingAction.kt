package com.vivanov.tinkoffnews.common.domain.actions

/**
 * @author Vladimir Ivanov
 */
sealed class LoadingAction(
    val viewKey: String,
    val loading: Boolean
) : Action {

    class Show(viewKey: String) : LoadingAction(viewKey, true)

    class Hide(viewKey: String) : LoadingAction(viewKey, false)
}
