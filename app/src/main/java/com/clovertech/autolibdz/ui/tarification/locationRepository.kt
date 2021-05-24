package com.clovertech.autolibdz.ui.tarification

import androidx.lifecycle.MutableLiveData
import com.clovertech.autolibdz.APIs.ApiClientCars
import com.clovertech.autolibdz.DataClasses.Rental
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class locationRepository {

    suspend fun createLocation(location: Rental): MutableLiveData<Rental?> {
        val data= MutableLiveData<Rental?>()

        ApiClientCars.apiService?.CreateRental(location)?.enqueue(object : Callback<Rental> {
            override fun onFailure(call: Call<Rental>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<Rental>, response: Response<Rental>) {
                val res = response.body()
                if (response.code() == 201 && res!=null){
                    data.value = res!!
                }else{
                    data.value = null
                }
            }
        })
        return data
    }
}