package com.clovertech.autolibdz.ui.listcars

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolibdz.APIs.ApiClientCars
import com.clovertech.autolibdz.Adapters.MyAdapter
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.ViewModelCars
import com.clovertech.autolibdz.ViewModel.ViewModelCarsFactory
import com.clovertech.autolibdz.repository.Repository
import kotlinx.android.synthetic.main.fragment_list_car.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class ListCarsFragment : Fragment() {

    private lateinit var liscarsViewMddel: ListCarsViewModel
    private lateinit var viewModel : ViewModelCars
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
        var data = mutableListOf<Vehicle>()

        val repository=Repository()
        val carModelFactory=ViewModelCarsFactory(repository)
        viewModel=ViewModelProvider(this,carModelFactory).get(ViewModelCars::class.java)
        viewModel.getCarsByStatus("available")
      /*  viewModel.myResponse.observe(this, Observer {
            response ->
            Log.d("Response", response.vehicletype)}
        )*/
        //data= executeCall() as MutableList<Vehicle>
        list_cars.apply {
            list_cars.layoutManager = LinearLayoutManager(activity)
            list_cars.adapter = MyAdapter(context,viewModel.myResponse)
        }

    }

    private fun executeCall():List<Vehicle>{
        var data = mutableListOf<Vehicle>()

        val executorService = Executors.newFixedThreadPool(4)
        //AsyncTask.execute(){
        executorService.execute { CoroutineScope(Dispatchers.Main).launch {

            try {
                val response = ApiClientCars.apiService.getCarsListByState("available")

                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    if (content != null) {
                        for (v in content){
                            data.add(v)
                        }
                        //data=content
                        Toast.makeText(
                            activity,
                            "prix: ${content[0].unitpriceperday}",

                            Toast.LENGTH_LONG
                        ).show()
                    }

                } else {
                    Toast.makeText(
                        activity,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    activity,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        }

        return  data

    }
}