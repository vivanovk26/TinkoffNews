package com.vivanov.tinkoffnews.presentation.delegates

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vivanov.tinkoffnews.presentation.states.ListState
import com.vivanov.tinkoffnews.presentation.views.list.ArticlesListAdapter
import domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal class ListViewDelegate(
    private val recyclerView: RecyclerView
) : BaseViewDelegate<ListState<Article>>() {

    private val adapter: ListAdapter<Article, *> = ArticlesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

    override fun shouldUpdate(state: ListState<Article>): Boolean {
        return state.items != adapter.currentList
    }

    override fun updateInternal(state: ListState<Article>) {
        adapter.submitList(state.items)
    }
}
