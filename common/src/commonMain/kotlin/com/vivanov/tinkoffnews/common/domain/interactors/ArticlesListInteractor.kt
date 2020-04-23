package com.vivanov.tinkoffnews.common.domain.interactors

import com.vivanov.tinkoffnews.common.domain.actions.ActionListener

/**
 * @author Vladimir Ivanov
 */
interface ArticlesListInteractor : Interactor {

    fun refreshArticles(actionListener: ActionListener)

    fun loadArticles(actionListener: ActionListener)
}
