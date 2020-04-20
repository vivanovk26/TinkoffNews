package com.vivanov.tinkoffnews.presentation.views.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vivanov.tinkoffnews.R
import com.vivanov.tinkoffnews.common.domain.model.Article
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesListAdapter : ListAdapter<Article, ArticlesListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val itemView = viewHolder.itemView
        val article = getItem(position)
        itemView.titleTextView.text = article.name
        itemView.descriptionTextView.text = article.text
    }

    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView)

    class DiffCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}
