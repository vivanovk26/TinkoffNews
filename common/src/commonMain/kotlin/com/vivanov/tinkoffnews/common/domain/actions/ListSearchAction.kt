package com.vivanov.tinkoffnews.common.domain.actions

/**
 * @author Vladimir Ivanov
 */
class ListSearchAction<T>(
    val items: List<T>,
    val searchText: String
) : Action
