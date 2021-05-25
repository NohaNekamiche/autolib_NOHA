package com.clovertech.autolibdz.APIs

import com.clovertech.autolibdz.DataClasses.Vehicle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CarsListByStateAPI {
    @GET("vehiclesListByState/{state}")
    suspend fun getCarsListByState(@Path("state") state : String): Response<Vehicle>
}