package com.vivanov.tinkoffnews.presentation.actions

import domain.actions.Action
import java.io.Serializable

/**
 * @author Vladimir Ivanov
 */
internal class ListAction<T : Serializable>(
    val items: List<T>
) : Action
