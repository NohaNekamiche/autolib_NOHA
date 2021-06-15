package com.clovertech.autolibdz.ui.tarification

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.clovertech.autolibdz.DataClasses.Rental
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.RentalViewModel
import com.clovertech.autolibdz.ViewModel.RentalViewModelFactory
import com.clovertech.autolibdz.repository.RentalRepository
import kotlinx.android.synthetic.main.fragment_car_details.*
import kotlinx.android.synthetic.main.tarification.*
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class TarificationFragment : Fragment(){
    private lateinit var tarificationViewModel: TarificationViewModel
    private lateinit var rentalViewModel: RentalViewModel
    val typepaiement = arrayOf("Jour", "Heur")
    val cardslist = arrayOf("Credit Card", "Carte d'abonnement")
    var days = 0
    var totalprice = 0
    var idrental=-1
    lateinit var type:String
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
        val id=arguments?.getInt("id")
        val mod=arguments?.getString("model")
        model.text=mod
        val img=arguments?.getString("img")
        Glide.with(context).load(img).into(img_v)
        val uni_hr=arguments?.getInt("hr")
        val uni_jr=arguments?.getInt("jr")
        brand.text=arguments?.getString("brand")
        val repository = RentalRepository()
        val factory = RentalViewModelFactory(repository)
        rentalViewModel = ViewModelProvider(this,factory)
                .get(RentalViewModel::class.java)
        //choix de cards
        card_choix.adapter=ArrayAdapter<String>(requireActivity(),android.R.layout.simple_list_item_1,cardslist)
        card_choix.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                type=cardslist.get(p2)
            }

        }
        //tyoe de paiement :)
        type_spinner.adapter=ArrayAdapter<String>(requireActivity(),android.R.layout.simple_list_item_1,typepaiement
        )
        type_spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
              if(typepaiement.get(p2)=="jour"){
                  plus.setOnClickListener {
                      var d:Int=  duree.text.toString().toInt()
                      d += 1
                      days=d
                      duree.setText(d.toString())
                      totalprice=(d* uni_jr!!)
                      total.setText(totalprice.toString())
                  }
                  moins.setOnClickListener {
                      var d:Int=  duree.text.toString().toInt()
                      if(d-1>=0){
                          d -= 1
                          days=d
                          totalprice=(d* uni_jr!!)
                          duree.setText(d.toString())
                          total.setText(totalprice.toString())
                      }
                      else{
                          Toast.makeText(activity,"Vous pouvez pas Avoir une durée < 0",Toast.LENGTH_SHORT).show()
                      }}
              }
                else{
                  plus.setOnClickListener {
                      var d:Int=  duree.text.toString().toInt()
                      d += 1
                      days=d
                      duree.setText(d.toString())
                      totalprice=(d* uni_hr!!)
                      total.setText(totalprice.toString())
                  }
                  moins.setOnClickListener {
                      var d:Int=  duree.text.toString().toInt()
                      if(d-1>=0){
                          d -= 1
                          days=d
                          totalprice=(d* uni_hr!!)
                          duree.setText(d.toString())
                          total.setText(totalprice.toString())
                      }
                      else{
                          Toast.makeText(activity,"Vous pouvez pas Avoir une durée < 0",Toast.LENGTH_SHORT).show()
                      }}
              }
            }

        }

        pay.setOnClickListener{
            val date_time= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
                        .withZone(ZoneOffset.UTC)
                        .format(Instant.now())
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            val t=LocalTime.now()
            val d= LocalDate.now()
            Toast.makeText(context,"id $id",Toast.LENGTH_LONG).show()
            val rental=
                    id?.let {
                        Rental(0,27, it,date_time,LocalTime.now().toString(),d.plusDays(2).toString()+" "+t.toString(),
                                t.toString(),d.plusDays(2).toString()+" "+t.toString(),t.toString(),type,
                                1,1,"active")
                    }


            rental?.let { rentalViewModel.addRental(it) }
            rentalViewModel.rentalResponse
                    .observe(viewLifecycleOwner, Observer {
                        response ->

                        if (response.isSuccessful){
                            idrental= response.body()!!.idRental


                            Log.e("Push",response.body().toString())
                            Log.e("Push",response.code().toString())

                            Toast.makeText(requireContext(),"sent sucessfully",Toast.LENGTH_SHORT).show()
                        }else{
                            Log.e("Push",response.body().toString())
                            Log.e("Push",response.code().toString())
                            Toast.makeText(requireContext(),"Alert",Toast.LENGTH_SHORT).show()
                        }
                    })
            val bundle = bundleOf("total" to totalprice,"idrental" to idrental)
            view?.findNavController()?.navigate(R.id.action_nav_slideshow_to_nav_card,bundle)

        }



    }
}
