package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.presentation.states.ListStateImpl
import domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal object ArticlesListReducer : ListReducer<Article>() {

    override fun createListState(items: List<Article>): ListStateImpl<Article> = ListStateImpl(items)
}
