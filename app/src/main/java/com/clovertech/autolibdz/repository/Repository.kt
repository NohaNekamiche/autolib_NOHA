package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.utils.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getCarsListByState(status :String): Response<MutableList<Vehicle>> {
        return RetrofitInstance.ApiCarsByStatus.getCarsListByState(status)
    }
}