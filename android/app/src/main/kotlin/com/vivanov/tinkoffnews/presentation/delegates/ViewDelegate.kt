package com.vivanov.tinkoffnews.presentation.delegates

import android.content.Intent
import android.os.Bundle
import com.vivanov.tinkoffnews.presentation.states.State

/**
 * @author Vladimir Ivanov
 */
internal interface ViewDelegate<T : State> {

    fun update(state: T)

    fun onNewIntent(intent: Intent) = Unit

    fun onCreate(savedInstanceState: Bundle?) = Unit

    fun onStart() = Unit

    fun onResume() = Unit

    fun onPause() = Unit

    fun onStop() = Unit

    fun onDestroy() = Unit

    fun onSaveInstanceState(outState: Bundle) = Unit
}
