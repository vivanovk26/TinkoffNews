package com.vivanov.tinkoffnews.presentation.delegates

import android.os.Bundle
import androidx.core.view.isVisible
import com.vivanov.tinkoffnews.presentation.states.ToolbarSearchState
import com.vivanov.tinkoffnews.presentation.views.ToolbarSearch
import kotlinx.android.synthetic.main.view_toolbar_search.view.*

/**
 * @author Vladimir Ivanov
 */
internal class ToolbarSearchDelegate(
    private val toolbarSearch: ToolbarSearch,
    private val toolbarSearchListener: ToolbarSearch.ToolbarSearchListener
) : BaseViewDelegate<ToolbarSearchState>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        toolbarSearch.setToolbarSearchListener(toolbarSearchListener)
    }

    override fun shouldUpdate(state: ToolbarSearchState): Boolean {
        return toolbarSearch.searchLinearLayout.isVisible != state.searchVisible ||
                toolbarSearch.searchEditText.text.toString() != state.searchText
    }

    override fun updateInternal(state: ToolbarSearchState) {
        toolbarSearch.update(state)
    }

    override fun onDestroy() {
        toolbarSearch.onDestroy()
    }

    override fun onBackPressed(): Boolean {
        return toolbarSearch.handleBackPressed()
    }
}
