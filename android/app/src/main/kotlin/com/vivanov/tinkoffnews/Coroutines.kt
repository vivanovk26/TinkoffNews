package com.vivanov.tinkoffnews

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> SendChannel<T>.sendOnMain(action: T) {
    CoroutineScope(Dispatchers.Main).launch {
        send(action)
    }
}

fun <T> Flow<T>.collectOnMain(func: (action: T) -> Unit, nullableJob: Job? = null) {
    val coroutineContext = nullableJob?.let { job ->
        Dispatchers.Main + job
    } ?: Dispatchers.Main
    CoroutineScope(coroutineContext).launch {
        collect { action ->
            func.invoke(action)
        }
    }
}
