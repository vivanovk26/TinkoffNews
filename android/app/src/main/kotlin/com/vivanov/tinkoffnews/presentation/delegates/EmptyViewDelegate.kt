package com.vivanov.tinkoffnews.presentation.delegates

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.vivanov.tinkoffnews.presentation.states.EmptyState
import com.vivanov.tinkoffnews.presentation.views.EmptyView

/**
 * @author Vladimir Ivanov
 */
internal class EmptyViewDelegate(
    private val emptyView: EmptyView
) : BaseViewDelegate<EmptyState>() {

    override fun shouldUpdate(state: EmptyState): Boolean {
        return (state.emptyData == null && emptyView.isVisible) || (state.emptyData != null && emptyView.isGone)
    }

    override fun updateInternal(state: EmptyState) {
        emptyView.isGone = state.emptyData == null
        state.emptyData?.let { emptyData -> emptyView.setEmptyData(emptyData) }
    }
}
