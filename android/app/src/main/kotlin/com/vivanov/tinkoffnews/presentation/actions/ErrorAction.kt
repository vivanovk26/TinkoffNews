package com.vivanov.tinkoffnews.presentation.actions

import domain.actions.Action

/**
 * @author Vladimir Ivanov
 */
internal class ErrorAction(
    val throwable: Throwable
) : Action
