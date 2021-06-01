package com.clovertech.autolibdz.ui.tarification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.R
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
        plus.setOnClickListener {
          var d:Int=  duree.text.toString().toInt()
            d += 1
            duree.setText(d.toString())
        }
        moins.setOnClickListener {
            var d:Int=  duree.text.toString().toInt()
            if(d-1>=0){
            d -= 1
            duree.setText(d.toString())}
            else{
                Toast.makeText(activity,"Vous pouvez pas Avoir une durée < 0",Toast.LENGTH_SHORT).show()
            }

        }
    }
}