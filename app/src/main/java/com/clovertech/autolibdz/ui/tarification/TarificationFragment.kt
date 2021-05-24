package com.clovertech.autolibdz.ui.tarification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.clovertech.autolibdz.APIs.ApiClientCars
import com.clovertech.autolibdz.APIs.ApiServiceCars
import com.clovertech.autolibdz.DataClasses.Rental
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.R
import kotlinx.android.synthetic.main.fragment_car_details.*
import kotlinx.android.synthetic.main.tarification.*
import kotlinx.android.synthetic.main.tarification.type
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TarificationFragment : Fragment() {


    private lateinit var tarificationViewModel: TarificationViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tarificationViewModel =
            ViewModelProvider(this).get(TarificationViewModel::class.java)
        val root = inflater.inflate(R.layout.tarification, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val id=arguments?.getString("id")
        val mod=arguments?.getString("model")
        val img=arguments?.getString("img")
        val uni_hr=arguments?.getString("hr")
        val uni_jr=arguments?.getString("jr")
        Glide.with(context).load(img).into(img_v)
        model.text=mod
        brand.text=arguments?.getString("brand")
        val typepaiement = resources.getStringArray(R.array.typepaiement)
       // val  typecard= arrayOf("Carte credit","Carte d'abonnement")
        val rental=Rental(12,
            3,
        "2021-04-05T00:00:00.000Z",
        "10:00:00",
        "2021-04-06T00:00:00.000Z",
        "10:00:00",
        "2021-05-07T00:00:00.000Z",
        "10:00:00",
        "day",
        1,
        1,
        "active")
        pay.setOnClickListener {
            /*val req=ApiClientCars.apiService.CreateRental(rental)
            req.enqueue(object: Callback<Rental> {

                override fun onResponse(call: Call<Rental>, response: Response<Rental>) {
                    if (response.isSuccessful) {
                    }
                    override fun onFailure(call: Call<Rental>, t: Throwable) {
                        Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
                    }
                })
*/
            }
       /* try {
            val request = ApiClientCars.apiService.CreateRental(rental)
            request.enqueue(object: Callback<Vehicle> {

                override fun onResponse(call: Call<Vehicle>, response: Response<Vehicle>) {
                    if (response.isSuccessful) {
                    }
                    override fun onFailure(call: Call<Vehicle>, t: Throwable) {
                        Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
                    }
                })


    } catch (e: Exception) {
        Toast.makeText(
            activity,
            "Error Occurred: ${e.message}",
            Toast.LENGTH_LONG
        ).show()
    }*/
        if (type != null) {
            val adapter = activity?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item, typepaiement)
            }
            type.adapter = adapter
            type.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(activity,
                         "type: " +
                                "" + typepaiement[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

     /*   if(card_choix!=null){
            val adapterCard=activity?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item, typepaiement)
            }
            card_choix.adapter=adapterCard
            card_choix.onItemSelectedListener=object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(activity,
                        "type: " , Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }


        }*/
    }
    suspend fun createLocation(location: Rental): MutableLiveData<Rental?> {
        val data= MutableLiveData<Rental?>()

        ApiClientCars.apiService?.CreateRental(location)?.enqueue(object : Callback<Rental>{
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