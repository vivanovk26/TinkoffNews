package com.vivanov.tinkoffnews.presentation.views

import android.os.Bundle
import com.vivanov.tinkoffnews.R
import com.vivanov.tinkoffnews.common.presentation.LoadingKeys
import com.vivanov.tinkoffnews.presentation.MVPManager
import com.vivanov.tinkoffnews.presentation.delegates.*
import com.vivanov.tinkoffnews.presentation.presenters.MainPresenter
import com.vivanov.tinkoffnews.presentation.states.MainState
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Vladimir Ivanov
 */
internal class MainActivity : BaseActivity<MainPresenter, MainState>() {

    override val presenter: MainPresenter = MVPManager.getPresenter(MainPresenter::class)

    override fun createViewDelegates(): List<ViewDelegate<in MainState>> {
        return listOf(
            ToolbarSearchDelegate(toolbarSearch, presenter),
            ListViewDelegate(recyclerView, presenter),
            LoadingViewDelegate(
                mapOf(
                    LoadingKeys.INITIAL_KEY to placeholderView,
                    LoadingKeys.REFRESH_KEY to swipeRefreshLayout
                )
            ),
            EmptyViewDelegate(emptyView),
            ErrorViewDelegate(errorTextView)
        )
    }

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.TinkoffNewsTheme)

        super.onCreate(savedInstanceState)

        initUI()
    }

    private fun initUI() {
        swipeRefreshLayout.setOnRefreshListener {
            presenter.refresh()
        }
    }
}
