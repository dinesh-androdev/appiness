package com.appiness

interface TaskCallback<T> {

    fun onComplete(result: T?)
    fun onException(t: Throwable?)

}