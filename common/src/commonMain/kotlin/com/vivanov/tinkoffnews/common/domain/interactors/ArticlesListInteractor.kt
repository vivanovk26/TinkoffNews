package com.vivanov.tinkoffnews.common.domain.interactors

import com.vivanov.tinkoffnews.common.domain.actions.ActionListener

/**
 * @author Vladimir Ivanov
 */
interface ArticlesListInteractor : Interactor {

    fun loadArticles(actionListener: ActionListener)

    fun searchArticles(actionListener: ActionListener, searchText: String)

    fun refreshArticles(actionListener: ActionListener)
}
