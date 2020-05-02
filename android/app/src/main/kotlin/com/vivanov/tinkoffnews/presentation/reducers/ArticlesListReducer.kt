package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.common.domain.actions.Action
import com.vivanov.tinkoffnews.common.domain.actions.UpdateBookmarkAction
import com.vivanov.tinkoffnews.common.domain.model.Article
import com.vivanov.tinkoffnews.presentation.states.ListState
import com.vivanov.tinkoffnews.presentation.states.ListStateImpl

/**
 * @author Vladimir Ivanov
 */
internal object ArticlesListReducer : ListReducer<Article>() {

    override fun createListState(items: List<Article>): ListStateImpl<Article> = ListStateImpl(items)

    override fun reduce(state: ListState<Article>, action: Action): ListState<Article> {
        return if (action is UpdateBookmarkAction) {
            val index = state.items.indexOfFirst { article -> article.id == action.article.id }
            val newList = state.items.toMutableList()
            newList[index] = action.article
            ListStateImpl(newList.toList())
        } else {
            super.reduce(state, action)
        }
    }
}
