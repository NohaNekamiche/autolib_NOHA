package com.clovertech.autolibdz.ui.tarification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.cardetails.CarDetailsViewModel
import kotlinx.android.synthetic.main.tarification.*

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
      /*  val id=arguments?.getString("id")
        val model=arguments?.getString("model")
        val img=arguments?.getString("img")
        val uni_hr=arguments?.getString("hr")
        val uni_jr=arguments?.getString("jr")*/

        val typepaiement = resources.getStringArray(R.array.typepaiement)
        val  typecard= arrayOf("Carte credit","Carte d'abonnement")
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

        if(card_choix!=null){
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
                        "type: " +
                                "" + typecard.getSelectedItem().toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }


        }
    }

}