package com.vivanov.tinkoffnews.data.providers

import androidx.annotation.StringRes

/**
 * @author Vladimir Ivanov
 */
internal interface ResourceProvider {

    fun getString(@StringRes resId: Int): String
}
