package com.clovertech.autolibdz.APIs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object CouroutinePromo {
    fun<T:Any> ioThenMain(work:suspend(()->T?), callback: (T?) -> Unit)=
        CoroutineScope(Dispatchers.Main).launch {
            val data= CoroutineScope(Dispatchers.IO).async rt@{
                return@rt work()
            }.await()
            callback(data)
        }
}