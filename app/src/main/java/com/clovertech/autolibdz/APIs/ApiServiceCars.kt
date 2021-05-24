package com.clovertech.autolibdz.APIs


import com.clovertech.autolibdz.DataClasses.Rental
import com.clovertech.autolibdz.DataClasses.Vehicle
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceCars {

    @GET("vehiclesList")
    suspend fun getAllVehicules(): Response<MutableList<Vehicle>>

    @GET("vehiclesListByState/{state}")
    suspend fun getCarsListByState(@Path("state") state : String): Response<MutableList<Vehicle>>
    @FormUrlEncoded
    @POST("addRental")
    fun CreateRental(@Body rental: Rental) : Call<Rental>

    @GET("/vehiclesList")
    suspend fun gevehiclesList(): Response<ResponseBody>


}