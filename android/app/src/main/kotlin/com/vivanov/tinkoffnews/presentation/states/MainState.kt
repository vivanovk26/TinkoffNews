package com.vivanov.tinkoffnews.presentation.states

import com.vivanov.tinkoffnews.common.domain.model.Article
import com.vivanov.tinkoffnews.common.presentation.LoadingKeys

/**
 * @author Vladimir Ivanov
 */
internal data class MainState(
    val toolbarSearchState: ToolbarSearchState = ToolbarSearchStateImpl(
        searchText = "",
        searchVisible = false
    ),
    val listState: ListState<Article> = ListStateImpl(
        items = emptyList()
    ),
    val loadingState: LoadingState = LoadingStateImpl(
        loadingStateMap = mapOf(
            LoadingKeys.INITIAL_KEY to false,
            LoadingKeys.REFRESH_KEY to false
        )
    ),
    val emptyState: EmptyState = EmptyStateImpl(
        emptyData = null
    ),
    val errorState: ErrorState = ErrorStateImpl(
        error = null
    )
) : ToolbarSearchState by toolbarSearchState,
    ListState<Article> by listState,
    LoadingState by loadingState,
    EmptyState by emptyState,
    ErrorState by errorState
