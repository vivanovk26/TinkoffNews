package com.vivanov.tinkoffnews.presentation.states

import com.vivanov.tinkoffnews.common.domain.model.Article
import com.vivanov.tinkoffnews.common.presentation.LoadingKeys

/**
 * @author Vladimir Ivanov
 */
internal data class MainState(
    val listState: ListState<Article> = ListStateImpl(emptyList()),
    val loadingState: LoadingState = LoadingStateImpl(
        mapOf(
            LoadingKeys.INITIAL_KEY to false,
            LoadingKeys.REFRESH_KEY to false
        )
    ),
    val emptyState: EmptyState = EmptyStateImpl(null),
    val errorState: ErrorState = ErrorStateImpl(null)
) : ListState<Article> by listState,
    LoadingState by loadingState,
    EmptyState by emptyState,
    ErrorState by errorState
