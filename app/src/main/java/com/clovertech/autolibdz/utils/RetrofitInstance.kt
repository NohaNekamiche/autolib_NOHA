package com.clovertech.autolibdz.utils
import com.clovertech.autolibdz.APIs.ApiClientCars
import com.clovertech.autolibdz.APIs.ApiServiceCars
import com.clovertech.autolibdz.APIs.CarsListByStateAPI
import com.clovertech.autolibdz.utils.Constants.Companion.Cars_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofitCars by lazy {
        Retrofit.Builder()
                .baseUrl(Cars_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val ApiCarsByStatus : CarsListByStateAPI by lazy {
            retrofitCars.create(CarsListByStateAPI::class.java)
    }

}