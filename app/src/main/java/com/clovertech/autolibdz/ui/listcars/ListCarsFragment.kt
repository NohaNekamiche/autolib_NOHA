package com.clovertech.autolibdz.ui.listcars

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolibdz.APIs.CarsApi
import com.clovertech.autolibdz.Adapters.MyCarAdapter
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.ViewModelCars
import com.clovertech.autolibdz.ViewModel.ViewModelCarsFactory
import com.clovertech.autolibdz.repository.CarsRepository
import kotlinx.android.synthetic.main.fragment_list_car.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListCarsFragment : Fragment() {

    private lateinit var liscarsViewMddel: ListCarsViewModel
    private lateinit var viewModel : ViewModelCars
    private lateinit var carsFactory: ViewModelCarsFactory
    private lateinit var templist:List<Vehicle>

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

        val carsApi=CarsApi()
        val repository=CarsRepository(carsApi)

        var adapter:MyCarAdapter
       carsFactory=ViewModelCarsFactory(repository)
        viewModel=ViewModelProvider(this,carsFactory).get(ViewModelCars::class.java)

        viewModel.getListCarsByStat("available")
       // Toast.makeText(requireContext(),viewModel.carsByStat.toString(),Toast.LENGTH_LONG)
        viewModel.carsByStat.observe(viewLifecycleOwner, Observer { cars->
            list_cars.also {
                it.layoutManager=LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter= MyCarAdapter(requireContext(),cars)
                adapter= it.adapter as MyCarAdapter
                //it.adapter.setCarsList(cars)
                //adapter.setCarsList(cars)
                var carsListFiltered: ArrayList<Vehicle> = ArrayList()



            }
            })

        }





    }

    private fun filteredList(searchVal:String,list :List<Vehicle>): ArrayList<Vehicle> {
        var carsListFiltered: ArrayList<Vehicle> = ArrayList()
        for(item in list){
            if(searchVal==item.vehicletype||searchVal==item.vehiclemodel||searchVal==item.vehiclebrand){
                carsListFiltered.add(item)
            }
        }
        return carsListFiltered

    }
