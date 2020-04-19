package domain.interactors

import domain.model.Article

/**
 * @author Vladimir Ivanov
 */
interface InteractorListener { // TODO Generic iOS support.

    fun onStart(): Unit = Unit

    fun onSuccess(result: List<Article>): Unit = Unit

    fun onError(throwable: Throwable): Unit = Unit

    fun onCompletion(): Unit = Unit
}
