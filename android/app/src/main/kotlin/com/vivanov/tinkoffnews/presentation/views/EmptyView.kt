package com.vivanov.tinkoffnews.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.vivanov.tinkoffnews.R
import com.vivanov.tinkoffnews.presentation.model.EmptyData
import kotlinx.android.synthetic.main.view_empty.view.*

/**
 * @author Vladimir Ivanov
 */
internal class EmptyView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(getContext(), R.layout.view_empty, this)
    }

    fun setEmptyData(emptyData: EmptyData) {
        imageView.setImageDrawable(ContextCompat.getDrawable(context, emptyData.iconRes))
        titleTextView.text = emptyData.title
        descriptionTextView.text = emptyData.description
    }
}
