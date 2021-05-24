package com.clovertech.autolibdz.APIs

import retrofit2.http.Path

class ApiHelper(private val apiService: ApiServiceCars) {

    suspend fun getAllVehicules()=apiService.getAllVehicules()
   // suspend fun getCarsListByState(@Path("state") state : String)=apiService.getCarsListByState( state : String)


}