package com.vivanov.tinkoffnews.presentation.states

import java.io.Serializable

/**
 * @author Vladimir Ivanov
 */
internal interface ListState<T : Serializable> : State {

    val items: List<T>
}
