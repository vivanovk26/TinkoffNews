package com.vivanov.tinkoffnews.common.domain.interactors

import com.vivanov.tinkoffnews.common.domain.actions.ActionListener
import com.vivanov.tinkoffnews.common.domain.model.Article

/**
 * @author Vladimir Ivanov
 */
interface ArticlesListInteractor : Interactor {

    fun loadArticles(actionListener: ActionListener)

    fun searchArticles(actionListener: ActionListener, searchText: String)

    fun updateBookmark(actionListener: ActionListener, article: Article)
}
