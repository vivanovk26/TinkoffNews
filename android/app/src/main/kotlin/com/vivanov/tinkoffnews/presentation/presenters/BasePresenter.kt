package com.vivanov.tinkoffnews.presentation.presenters

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.vivanov.tinkoffnews.presentation.states.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author Vladimir Ivanov
 */
internal abstract class BasePresenter<T : State> : Presenter<T>, CoroutineScope by MainScope() {

    override val stateLiveData: MutableLiveData<T> = MutableLiveData()

    protected abstract fun createState(): T

    override fun onViewAttached(savedInstanceState: Bundle?) {
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_KEY)) {
            stateLiveData.postValue(savedInstanceState.getSerializable(STATE_KEY) as T)
        } else {
            stateLiveData.postValue(createState())
            onPresenterCreated()
        }
    }

    protected open fun onPresenterCreated() = Unit

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(STATE_KEY, stateLiveData.value)
    }

    override fun onViewDetached() = Unit

    override fun onDestroy() {
        cancel()
    }

    private companion object {

        const val STATE_KEY: String = "STATE_KEY"
    }
}
