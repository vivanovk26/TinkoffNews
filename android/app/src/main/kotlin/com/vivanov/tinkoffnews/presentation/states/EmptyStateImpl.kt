package com.vivanov.tinkoffnews.presentation.states

import com.vivanov.tinkoffnews.presentation.model.EmptyData

/**
 * @author Vladimir Ivanov
 */
internal class EmptyStateImpl(
    override val emptyData: EmptyData?
) : EmptyState
