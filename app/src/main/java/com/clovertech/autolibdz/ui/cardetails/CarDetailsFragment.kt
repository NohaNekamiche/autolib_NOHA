package com.clovertech.autolibdz.ui.cardetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.listcars.ListCarsViewModel

class CarDetailsFragment : Fragment() {


    private lateinit var carDetailViewModel: CarDetailsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        carDetailViewModel =
                ViewModelProvider(this).get(CarDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_car_details, container, false)

        return root
    }

}