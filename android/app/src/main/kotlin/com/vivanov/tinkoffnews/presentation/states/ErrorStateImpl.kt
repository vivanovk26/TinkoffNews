package com.vivanov.tinkoffnews.presentation.states

/**
 * @author Vladimir Ivanov
 */
internal class ErrorStateImpl(
    override val error: Throwable?
) : ErrorState
