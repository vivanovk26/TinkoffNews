package com.vivanov.tinkoffnews.presentation.states

import java.io.Serializable

/**
 * @author Vladimir Ivanov
 */
internal class ListStateImpl<T : Serializable>(
    override val items: List<T>
) : ListState<T>
