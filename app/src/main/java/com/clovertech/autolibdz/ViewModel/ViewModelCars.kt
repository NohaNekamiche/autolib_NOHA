package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelCars (private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Vehicle> = MutableLiveData()
    fun getCarsByStatus(Status :String) {
        viewModelScope.launch {
            val response: Response<MutableList<Vehicle>> = repository.getCarsListByState(Status)

        }
    }
}