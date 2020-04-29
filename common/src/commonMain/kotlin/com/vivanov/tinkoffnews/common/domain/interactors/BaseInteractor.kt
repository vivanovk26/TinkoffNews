package com.vivanov.tinkoffnews.common.domain.interactors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author Vladimir Ivanov
 */
internal abstract class BaseInteractor : Interactor, CoroutineScope by MainScope() {

    override fun onDestroy() {
        cancel()
    }
}
