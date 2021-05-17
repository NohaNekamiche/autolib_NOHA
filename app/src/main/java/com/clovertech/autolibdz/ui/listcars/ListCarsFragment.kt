package com.clovertech.autolibdz.ui.listcars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolibdz.Adapters.MyAdapter
import com.clovertech.autolibdz.DataClasses.Car
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_list_car.*

class ListCarsFragment : Fragment() {

    private lateinit var liscarsViewMddel: ListCarsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        liscarsViewMddel =
            ViewModelProvider(this).get(ListCarsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_list_car, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list_cars.apply {
            list_cars.layoutManager = LinearLayoutManager(activity)
            list_cars.adapter = MyAdapter(context,loadData())
        }
    }
    fun loadData():List<Car>{

        val data = mutableListOf<Car>()
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_pic))
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_2))


        data.add(Car(5000.5,"VOLKSWAGEN POLO6",14222,R.drawable.car_r))

        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_2))
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_pic))
        data.add(Car(5000.5,"VOLKSWAGEN POLO6",14222,R.drawable.car_r))
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_2))
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_pic))
        data.add(Car(5000.5,"VOLKSWAGEN POLO6",14222,R.drawable.car_r))
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_2))

        return data
    }
}