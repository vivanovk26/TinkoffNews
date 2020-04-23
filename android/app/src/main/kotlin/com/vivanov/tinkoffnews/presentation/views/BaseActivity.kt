package com.vivanov.tinkoffnews.presentation.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.vivanov.tinkoffnews.presentation.MVPManager
import com.vivanov.tinkoffnews.presentation.delegates.ViewDelegate
import com.vivanov.tinkoffnews.presentation.presenters.Presenter
import com.vivanov.tinkoffnews.presentation.states.State

/**
 * @author Vladimir Ivanov
 */
internal abstract class BaseActivity<T : Presenter<K>, K : State> : Activity(), LifecycleOwner {

    protected abstract val presenter: T
    private val lifecycleRegistry: LifecycleRegistry by lazy { LifecycleRegistry(this) }
    private val viewDelegates: List<ViewDelegate<in K>> by lazy { createViewDelegates() }

    protected open fun createViewDelegates(): List<ViewDelegate<in K>> = emptyList()

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        viewDelegates.forEach { viewDelegate -> viewDelegate.onNewIntent(intent) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutRes())
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        viewDelegates.forEach { viewDelegate -> viewDelegate.onCreate(savedInstanceState) }
        presenter.onViewAttached(savedInstanceState)
        presenter.stateLiveData.observe(this, Observer { state ->
            viewDelegates.forEach { viewDelegate -> viewDelegate.update(state) }
        })
    }

    override fun onStart() {
        super.onStart()

        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        viewDelegates.forEach { viewDelegate -> viewDelegate.onStart() }
    }

    override fun onResume() {
        super.onResume()

        viewDelegates.forEach { viewDelegate -> viewDelegate.onResume() }
    }

    override fun onPause() {
        super.onPause()

        viewDelegates.forEach { viewDelegate -> viewDelegate.onPause() }
    }

    override fun onStop() {
        super.onStop()

        viewDelegates.forEach { viewDelegate -> viewDelegate.onStop() }
    }

    override fun onDestroy() {
        presenter.onViewDetached()
        if (isFinishing) {
            presenter.onDestroy()
            MVPManager.removePresenter(presenter)
        }
        viewDelegates.forEach { viewDelegate -> viewDelegate.onDestroy() }

        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        presenter.onSaveInstanceState(outState)
        viewDelegates.forEach { viewDelegate -> viewDelegate.onSaveInstanceState(outState) }

        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (viewDelegates.none { viewDelegate -> viewDelegate.onBackPressed() }) {
            super.onBackPressed()
        }
    }
}
