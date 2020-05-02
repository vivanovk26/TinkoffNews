package com.vivanov.tinkoffnews.common.domain.actions

import com.vivanov.tinkoffnews.common.domain.model.Article

/**
 * @author Vladimir Ivanov
 */
class UpdateBookmarkAction(
    val article: Article
) : Action
