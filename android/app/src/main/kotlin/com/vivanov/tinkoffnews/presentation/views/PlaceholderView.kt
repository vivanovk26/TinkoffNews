package com.vivanov.tinkoffnews.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.vivanov.tinkoffnews.R
import com.vivanov.tinkoffnews.presentation.fadeIn
import com.vivanov.tinkoffnews.presentation.fadeOut
import kotlinx.android.synthetic.main.view_placeholder.view.*

/**
 * @author Vladimir Ivanov
 */
internal class PlaceholderView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes
    defStyleAttr: Int = 0,
    @StyleRes
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    @LayoutRes
    private val layoutRes: Int

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PlaceHolderView,
            defStyleAttr,
            defStyleRes
        ).apply {
            layoutRes = getResourceId(R.styleable.PlaceHolderView_layoutRes, 0)
        }
        inflate(getContext(), R.layout.view_placeholder, this)
        viewStub.layoutResource = layoutRes
        viewStub.inflate()
    }

    fun show() {
        shfl.startShimmer()
        fadeIn()
    }

    fun hide() {
        fadeOut()
        shfl.stopShimmer()
    }
}
