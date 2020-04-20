package com.vivanov.tinkoffnews.common.domain.actions

/**
 * @author Vladimir Ivanov
 */
class ErrorAction(
    val throwable: Throwable
) : Action
