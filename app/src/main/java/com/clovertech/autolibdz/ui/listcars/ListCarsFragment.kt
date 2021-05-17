package com.clovertech.autolibdz.ui.listcars

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.loader.content.AsyncTaskLoader
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolibdz.APIs.ApiClientCars
import com.clovertech.autolibdz.Adapters.MyAdapter
import com.clovertech.autolibdz.DataClasses.Car
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.R
import kotlinx.android.synthetic.main.fragment_list_car.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        var data = mutableListOf<Vehicle>()
        //data= executeCall() as MutableList<Vehicle>
        list_cars.apply {
            list_cars.layoutManager = LinearLayoutManager(activity)
            list_cars.adapter = MyAdapter(context,executeCall())
        }

    }
    fun loadData():List<Vehicle>{
      //  executeCall()
        val data = mutableListOf<Vehicle>()
      /*  data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_pic))
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_2))


        data.add(Car(5000.5,"VOLKSWAGEN POLO6",14222,R.drawable.car_r))

        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_2))
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_pic))
        data.add(Car(5000.5,"VOLKSWAGEN POLO6",14222,R.drawable.car_r))
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_2))
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_pic))
        data.add(Car(5000.5,"VOLKSWAGEN POLO6",14222,R.drawable.car_r))
        data.add(Car(4000.0,"VOLKSWAGEN POLO",12555,R.drawable.car_2))*/
        data.add(Vehicle(7, "Convertible","MITSUBISHI ECLIPSE GT SPYDER CONVERTIBLE 2009\n",
            "available",100,
            100,

        "MITSUBISHI ",

        "https://s3-alpha-sig.figma.com/img/9269/55d5/591f35085934ef6a48001529bb53130a?Expires=1621814400&Signature=PgirQB91Lfbnuqy46IkaPm2sEC5S38dn~3PW-GUUomT3Kg55IZMez-Sa73ihlY2a9YZOdB8F0XPvAJ6MSi95gRGxN7NwBC-W1xJsIPrWyviDwNwTms-PHfdIRkyWO92~XAPuyWIeKJPvkdBd8teLuxHBtCWT31wxONqm-9jFTaiZk-JsccKg0yopCC-sNFsus-otdKW5a2QQt2YtuiXR5NpusOMqoNfy2TfoYDTYxU8pJmAXuwOYBEvcFDxwK7JQMDQq73TxVm16co24bespeypCDJudhd2k9nrm2UHo8PIkUFhclVCT86P-Yld5PlQcRoELKeSn9h6eCK1fjs6WIA__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"
    ))
        data.add(Vehicle(7, "Convertible","MITSUBISHI ECLIPSE GT SPYDER CONVERTIBLE 2009\n",
            "available",100,
            100,

            "MITSUBISHI ",

            "https://s3-alpha-sig.figma.com/img/9269/55d5/591f35085934ef6a48001529bb53130a?Expires=1621814400&Signature=PgirQB91Lfbnuqy46IkaPm2sEC5S38dn~3PW-GUUomT3Kg55IZMez-Sa73ihlY2a9YZOdB8F0XPvAJ6MSi95gRGxN7NwBC-W1xJsIPrWyviDwNwTms-PHfdIRkyWO92~XAPuyWIeKJPvkdBd8teLuxHBtCWT31wxONqm-9jFTaiZk-JsccKg0yopCC-sNFsus-otdKW5a2QQt2YtuiXR5NpusOMqoNfy2TfoYDTYxU8pJmAXuwOYBEvcFDxwK7JQMDQq73TxVm16co24bespeypCDJudhd2k9nrm2UHo8PIkUFhclVCT86P-Yld5PlQcRoELKeSn9h6eCK1fjs6WIA__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"
        ))
        data.add(Vehicle(7, "Convertible","MITSUBISHI ECLIPSE GT SPYDER CONVERTIBLE 2009\n",
            "available",100,
            100,

            "MITSUBISHI ",

            "https://s3-alpha-sig.figma.com/img/9269/55d5/591f35085934ef6a48001529bb53130a?Expires=1621814400&Signature=PgirQB91Lfbnuqy46IkaPm2sEC5S38dn~3PW-GUUomT3Kg55IZMez-Sa73ihlY2a9YZOdB8F0XPvAJ6MSi95gRGxN7NwBC-W1xJsIPrWyviDwNwTms-PHfdIRkyWO92~XAPuyWIeKJPvkdBd8teLuxHBtCWT31wxONqm-9jFTaiZk-JsccKg0yopCC-sNFsus-otdKW5a2QQt2YtuiXR5NpusOMqoNfy2TfoYDTYxU8pJmAXuwOYBEvcFDxwK7JQMDQq73TxVm16co24bespeypCDJudhd2k9nrm2UHo8PIkUFhclVCT86P-Yld5PlQcRoELKeSn9h6eCK1fjs6WIA__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"
        ))
        data.add(Vehicle(7, "Convertible","MITSUBISHI ECLIPSE GT SPYDER CONVERTIBLE 2009\n",
            "available",100,
            100,

            "MITSUBISHI ",

            "https://s3-alpha-sig.figma.com/img/9269/55d5/591f35085934ef6a48001529bb53130a?Expires=1621814400&Signature=PgirQB91Lfbnuqy46IkaPm2sEC5S38dn~3PW-GUUomT3Kg55IZMez-Sa73ihlY2a9YZOdB8F0XPvAJ6MSi95gRGxN7NwBC-W1xJsIPrWyviDwNwTms-PHfdIRkyWO92~XAPuyWIeKJPvkdBd8teLuxHBtCWT31wxONqm-9jFTaiZk-JsccKg0yopCC-sNFsus-otdKW5a2QQt2YtuiXR5NpusOMqoNfy2TfoYDTYxU8pJmAXuwOYBEvcFDxwK7JQMDQq73TxVm16co24bespeypCDJudhd2k9nrm2UHo8PIkUFhclVCT86P-Yld5PlQcRoELKeSn9h6eCK1fjs6WIA__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"
        ))
        data.add(Vehicle(7, "Convertible","MITSUBISHI ECLIPSE GT SPYDER CONVERTIBLE 2009\n",
            "available",100,
            100,

            "MITSUBISHI ",

            "https://s3-alpha-sig.figma.com/img/9269/55d5/591f35085934ef6a48001529bb53130a?Expires=1621814400&Signature=PgirQB91Lfbnuqy46IkaPm2sEC5S38dn~3PW-GUUomT3Kg55IZMez-Sa73ihlY2a9YZOdB8F0XPvAJ6MSi95gRGxN7NwBC-W1xJsIPrWyviDwNwTms-PHfdIRkyWO92~XAPuyWIeKJPvkdBd8teLuxHBtCWT31wxONqm-9jFTaiZk-JsccKg0yopCC-sNFsus-otdKW5a2QQt2YtuiXR5NpusOMqoNfy2TfoYDTYxU8pJmAXuwOYBEvcFDxwK7JQMDQq73TxVm16co24bespeypCDJudhd2k9nrm2UHo8PIkUFhclVCT86P-Yld5PlQcRoELKeSn9h6eCK1fjs6WIA__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"
        ))
        return data
    }
    private fun executeCall():List<Vehicle>{
        var data = mutableListOf<Vehicle>()
        AsyncTask.execute(){
        CoroutineScope(Dispatchers.Main).launch {

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