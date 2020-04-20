package com.vivanov.tinkoffnews.presentation.reducers

import com.vivanov.tinkoffnews.common.domain.model.Article
import com.vivanov.tinkoffnews.presentation.states.ListStateImpl

/**
 * @author Vladimir Ivanov
 */
internal object ArticlesListReducer : ListReducer<Article>() {

    override fun createListState(items: List<Article>): ListStateImpl<Article> = ListStateImpl(items)
}
