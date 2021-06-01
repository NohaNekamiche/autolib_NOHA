package com.clovertech.autolibdz.utils

import com.clovertech.autolibdz.APIs.RentalApi
import com.clovertech.autolibdz.utils.Constants.Companion.Cars_BASE_URL
import com.clovertech.autolibdz.utils.Constants.Companion.Pricing_BASE_URL
import com.clovertech.autolibdz.utils.Constants.Companion.Rental_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofitGetCard by lazy {
        Retrofit.Builder()
                .baseUrl(Cars_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    private val retrofitGetPring by lazy {
        Retrofit.Builder()
                .baseUrl(Pricing_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    private val retrofitRental by lazy {
        Retrofit.Builder()
                .baseUrl(Rental_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    val AddRentalApi: RentalApi by lazy {
        retrofitRental.create(RentalApi::class.java)
    }
    private val retrofitAddCard by lazy {
        Retrofit.Builder()
                .baseUrl(Cars_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}