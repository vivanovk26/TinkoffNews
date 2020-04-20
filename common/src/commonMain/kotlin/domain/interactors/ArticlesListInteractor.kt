package domain.interactors

import domain.actions.ActionListener

/**
 * @author Vladimir Ivanov
 */
interface ArticlesListInteractor {

    fun getArticles(actionListener: ActionListener)
}
