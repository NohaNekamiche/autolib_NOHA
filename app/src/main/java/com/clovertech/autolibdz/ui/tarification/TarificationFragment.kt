package com.clovertech.autolibdz.ui.tarification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.cardetails.CarDetailsViewModel

class TarificationFragment : Fragment() {


    private lateinit var tarificationViewModel: TarificationViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tarificationViewModel =
            ViewModelProvider(this).get(TarificationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_car_details, container, false)

        return root
    }
}