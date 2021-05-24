package com.clovertech.autolibdz.ui.listcars

import okhttp3.Interceptor
import okhttp3.Response

class CarInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            // TODO: Use your API Key provided by CoinMarketCap Professional API Developer Portal.
            .addHeader("X-CMC_PRO_API_KEY", "CMC_PRO_API_KEY")
            .build()

        return chain.proceed(newRequest)
    }
}