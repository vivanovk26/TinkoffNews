package com.vivanov.tinkoffnews.presentation.actions

import java.io.Serializable

/**
 * @author Vladimir Ivanov
 */
internal class ListAction<T : Serializable>(
    val items: List<T>
) : Action
