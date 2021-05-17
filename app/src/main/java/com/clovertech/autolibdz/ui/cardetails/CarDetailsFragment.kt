package com.clovertech.autolibdz.ui.cardetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.listcars.ListCarsViewModel
import kotlinx.android.synthetic.main.fragment_car_details.*

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val id=arguments?.getString("id")
        car.text=arguments?.getString("model")
        Glide.with(context).load(arguments?.getString("img")).into(img_car)
        val uni_hr=arguments?.getString("hr")
        val uni_jr=arguments?.getString("jr")
        button.setOnClickListener {  }
    }

}