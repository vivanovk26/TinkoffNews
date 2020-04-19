package com.vivanov.tinkoffnews.presentation.delegates

import android.view.View
import androidx.core.view.isVisible
import com.vivanov.tinkoffnews.presentation.states.LoadingState

/**
 * @author Vladimir Ivanov
 */
internal class LoadingViewDelegate(
    private val loadingView: View
) : BaseViewDelegate<LoadingState>() {

    override fun shouldUpdate(state: LoadingState): Boolean {
        return loadingView.isVisible != state.loading
    }

    override fun updateInternal(state: LoadingState) {
        loadingView.isVisible = state.loading
    }
}
