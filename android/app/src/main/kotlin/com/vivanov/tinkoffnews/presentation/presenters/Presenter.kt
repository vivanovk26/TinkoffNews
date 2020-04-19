package com.vivanov.tinkoffnews.presentation.presenters

import android.os.Bundle
import androidx.lifecycle.LiveData
import com.vivanov.tinkoffnews.presentation.states.State

/**
 * @author Vladimir Ivanov
 */
internal interface Presenter<T : State> {

    val stateLiveData: LiveData<T>

    fun onViewAttached(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState: Bundle)

    fun onViewDetached()

    fun onDestroy()
}
