package com.clovertech.autolibdz.APIs


import com.clovertech.autolibdz.DataClasses.Rental
import com.clovertech.autolibdz.DataClasses.Vehicle
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceCars {

    @GET("vehiclesList")
    suspend fun getAllVehicules(): Response<MutableList<Vehicle>>

    @GET("vehiclesListByState/{state}")
    suspend fun getCarsListByState(@Path("state") state : String): Response<MutableList<Vehicle>>

    @POST("addRental")
    suspend fun CreateRental(@Body rental: Rental) :Response<Rental>


}