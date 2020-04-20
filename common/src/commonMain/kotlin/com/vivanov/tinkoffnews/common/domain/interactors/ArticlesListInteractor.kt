package com.vivanov.tinkoffnews.common.domain.interactors

import com.vivanov.tinkoffnews.common.domain.actions.ActionListener

/**
 * @author Vladimir Ivanov
 */
interface ArticlesListInteractor {

    fun getArticles(actionListener: ActionListener)
}
