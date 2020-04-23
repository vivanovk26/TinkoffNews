package com.vivanov.tinkoffnews.presentation.delegates

import android.view.View
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.vivanov.tinkoffnews.presentation.states.LoadingState
import com.vivanov.tinkoffnews.presentation.views.PlaceholderView

/**
 * @author Vladimir Ivanov
 */
internal class LoadingViewDelegate(
    private val viewsMap: Map<String, View>
) : BaseViewDelegate<LoadingState>() {

    override fun shouldUpdate(state: LoadingState): Boolean {
        return viewsMap.map { (key, view) -> key to isInternalVisible(view) } != state.loadingStateMap
    }

    private fun isInternalVisible(view: View): Boolean {
        return when (view) {
            is SwipeRefreshLayout -> {
                view.isRefreshing
            }
            else -> view.isVisible
        }
    }

    override fun updateInternal(state: LoadingState) {
        viewsMap.forEach { (key, view) ->
            val newVisible = requireNotNull(state.loadingStateMap[key], { "This key: $key hadn't appeared in this map before" })
            if (isInternalVisible(view) != newVisible) {
                updateInternalView(view, newVisible)
            }
        }
    }

    private fun updateInternalView(view: View, visible: Boolean) {
        when (view) {
            is PlaceholderView -> {
                if (visible) {
                    view.show()
                } else {
                    view.hide()
                }
            }
            is SwipeRefreshLayout -> {
                view.isRefreshing = visible
            }
            else -> view.isVisible = visible
        }
    }
}
