package com.vivanov.tinkoffnews.presentation.states

import domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal data class MainState(
    val listState: ListState<Article> = ListStateImpl(emptyList()),
    val loadingState: LoadingState = LoadingStateImpl(false),
    val emptyState: EmptyState = EmptyStateImpl(null),
    val errorState: ErrorState = ErrorStateImpl(null)
) : ListState<Article> by listState,
    LoadingState by loadingState,
    EmptyState by emptyState,
    ErrorState by errorState
