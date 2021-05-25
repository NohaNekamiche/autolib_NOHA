package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.repository.CarsRepository
import com.clovertech.autolibdz.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelCarsFactory (
        private val  repository: CarsRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelCars(this.repository) as T
    }

}