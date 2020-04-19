package com.vivanov.tinkoffnews.presentation.delegates

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.vivanov.tinkoffnews.presentation.states.ErrorState

/**
 * @author Vladimir Ivanov
 */
internal class ErrorViewDelegate(
    private val errorView: View
) : BaseViewDelegate<ErrorState>() {

    override fun shouldUpdate(state: ErrorState): Boolean {
        return (state.error == null && errorView.isVisible) || (state.error != null && errorView.isGone)
    }

    override fun updateInternal(state: ErrorState) {
        errorView.isGone = state.error == null
    }
}
