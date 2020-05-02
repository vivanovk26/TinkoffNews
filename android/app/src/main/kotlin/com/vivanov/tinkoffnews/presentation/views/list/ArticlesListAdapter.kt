package com.vivanov.tinkoffnews.presentation.views.list

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vivanov.tinkoffnews.R
import com.vivanov.tinkoffnews.common.domain.model.Article
import com.vivanov.tinkoffnews.common.domain.model.DatabaseState
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_article.*

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesListAdapter(
    private val bookmarkClickListener: BookmarkClickListener
) : ListAdapter<Article, ArticlesListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int, payloads: List<Any>) {
        when {
            payloads.contains(Payload.BOOKMARK) -> viewHolder.bookmarkImageButton.setImageDrawable(
                getBookmarkImage(viewHolder.containerView.context, getItem(position).databaseState)
            )
            else -> super.onBindViewHolder(viewHolder, position, payloads)
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(getItem(position)) {
            viewHolder.titleTextView.text = name
            viewHolder.descriptionTextView.text = text
            viewHolder.sdrv.setImageURI(imageUrl)
            viewHolder.bookmarkImageButton.setImageDrawable(getBookmarkImage(viewHolder.containerView.context, databaseState))
            viewHolder.bookmarkImageButton.setOnClickListener {
                bookmarkClickListener.onBookmarkClick(this)
            }
        }
    }

    private fun getBookmarkImage(context: Context, databaseState: DatabaseState): Drawable? {
        return ContextCompat.getDrawable(
            context, if (databaseState == DatabaseState.IN_DATABASE) {
                R.drawable.ic_bookmark_enabled
            } else {
                R.drawable.ic_bookmark_disabled
            }
        )
    }

    class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer

    class DiffCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: Article, newItem: Article): Any? {
            if (oldItem.id == newItem.id &&
                oldItem.name == newItem.name &&
                oldItem.text == newItem.text &&
                oldItem.imageUrl == newItem.imageUrl &&
                oldItem.publicationDate == newItem.publicationDate &&
                oldItem.databaseState != newItem.databaseState
            ) {
                return Payload.BOOKMARK
            }
            return Payload.ALL
        }
    }

    enum class Payload {

        BOOKMARK, ALL
    }

    interface BookmarkClickListener {

        fun onBookmarkClick(article: Article)
    }
}
