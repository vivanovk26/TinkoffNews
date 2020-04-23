package com.vivanov.tinkoffnews.presentation.views

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.vivanov.tinkoffnews.R
import com.vivanov.tinkoffnews.presentation.hideKeyboard
import com.vivanov.tinkoffnews.presentation.showKeyboard
import com.vivanov.tinkoffnews.presentation.states.ToolbarSearchState
import kotlinx.android.synthetic.main.view_toolbar_search.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import ru.ldralighieri.corbind.widget.textChanges
import kotlin.math.hypot

/**
 * @author Vladimir Ivanov
 */
internal class ToolbarSearch
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes
    defStyleAttr: Int = 0,
    @StyleRes
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes), CoroutineScope by MainScope() {

    private val hint: String
    private val title: String
    private var cacheExpired: Boolean = false
    private var cachedSearchText: String = ""
    private var cachedSearchVisible: Boolean = false
    private var toolbarSearchListener: ToolbarSearchListener? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ToolbarSearch,
            defStyleAttr,
            defStyleRes
        ).apply {
            hint = getString(R.styleable.ToolbarSearch_hint) ?: ""
            title = getString(R.styleable.ToolbarSearch_title) ?: ""
        }
        inflate(getContext(), R.layout.view_toolbar_search, this)

        setupTitleView()
        setupSearchView()
    }

    private fun setupTitleView() {
        titleTextView.text = title
        searchImageButton.setOnClickListener { showSearchToolbar() }
    }

    private fun showSearchToolbar() {
        val centerStartX = toolbar.right
        val centerStartY = toolbar.bottom / 2
        val startRadius = 0f
        val endRadius =
            hypot(toolbar.width.toDouble(), toolbar.height.toDouble()).toFloat()

        val anim = ViewAnimationUtils.createCircularReveal(
            searchLinearLayout,
            centerStartX,
            centerStartY,
            startRadius,
            endRadius
        )
        anim.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)

                cachedSearchText = ""
                cachedSearchVisible = true
                searchEditText.showKeyboard()
                toolbarSearchListener?.onSearchVisibilityChanged(true)

                invalidateSearchViewVisibility()
            }
        })
        searchLinearLayout.isVisible = true
        anim.start()
    }

    private fun invalidateSearchViewVisibility() {
        titleLinearLayout.isGone = cachedSearchVisible
        searchLinearLayout.isVisible = cachedSearchVisible
        closeImageButton.isVisible = cachedSearchVisible && cachedSearchText.isNotEmpty()
    }

    private fun setupSearchView() {
        backImageButton.setOnClickListener {
            clearSearch()
            hideSearch()
        }
        searchEditText.hint = hint
        searchEditText.textChanges()
            .drop(1)
            .map { char ->
                closeImageButton.isVisible = char.isNotEmpty()
                char.toString()
            }
            .debounce(SEARCH_DEBOUNCE)
            .distinctUntilChanged()
            .onEach { text ->
                cachedSearchText = text
                toolbarSearchListener?.onSearchTextChanged(text)
            }
            .launchIn(this)
        closeImageButton.setOnClickListener {
            clearSearch()
        }
    }

    private fun clearSearch() {
        cachedSearchText = ""
        searchEditText.setText(cachedSearchText)

        invalidateSearchViewVisibility()
    }

    private fun hideSearch() {
        val centerStartX = toolbar.left
        val centerStartY = toolbar.bottom / 2
        val startRadius =
            hypot(toolbar.width.toDouble(), toolbar.height.toDouble()).toFloat()
        val endRadius = 0f

        val anim = ViewAnimationUtils.createCircularReveal(
            searchLinearLayout,
            centerStartX,
            centerStartY,
            startRadius,
            endRadius
        )
        anim.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)

                cachedSearchText = ""
                cachedSearchVisible = false
                searchEditText.hideKeyboard()
                toolbarSearchListener?.onSearchVisibilityChanged(false)

                invalidateSearchViewVisibility()
            }
        })
        anim.start()
    }

    fun setToolbarSearchListener(toolbarSearchListener: ToolbarSearchListener) {
        this.toolbarSearchListener = toolbarSearchListener
    }

    fun update(state: ToolbarSearchState) {
        if (cachedSearchText != state.searchText || cacheExpired) {
            cachedSearchText = state.searchText
            searchEditText.setText(state.searchText)
        }

        if (cachedSearchVisible != state.searchVisible || cacheExpired) {
            cachedSearchVisible = state.searchVisible

            invalidateSearchViewVisibility()
        }

        cacheExpired = false
    }

    fun handleBackPressed(): Boolean {
        when {
            cachedSearchVisible && cachedSearchText.isNotEmpty() -> clearSearch()
            cachedSearchVisible && cachedSearchText.isEmpty() -> hideSearch()
            else -> return false
        }
        return true
    }

    fun onDestroy() {
        cacheExpired = true
        cancel()
    }

    internal interface ToolbarSearchListener {

        fun onSearchTextChanged(text: String)

        fun onSearchVisibilityChanged(visible: Boolean)
    }

    private companion object {

        const val SEARCH_DEBOUNCE: Long = 250L
    }
}
