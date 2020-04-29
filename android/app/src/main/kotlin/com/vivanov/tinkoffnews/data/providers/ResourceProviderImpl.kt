package com.vivanov.tinkoffnews.data.providers

import android.content.Context

/**
 * @author Vladimir Ivanov
 */
internal class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, vararg args: Any): String {
        return context.getString(resId, *args)
    }
}
