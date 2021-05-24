package com.clovertech.autolibdz.ui.card

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolibdz.APIs.ApiClientCards
import com.clovertech.autolibdz.R
import kotlinx.android.synthetic.main.fragment_card.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CardFragment : Fragment() {


    private lateinit var cardViewModel: CardViewModel


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardViewModel =
            ViewModelProvider(this).get(CardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_card, container, false)

        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val add_card_fragment = AddCardFragment()
       val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        list_card.apply {
            list_card.layoutManager = LinearLayoutManager(activity)
            list_card.adapter = CardAdapter(context,executeCall())
        }
        add_card.setOnClickListener {
            add_card_fragment.show(fragmentManager,"add_card_fragment")
        }


    }
    private fun executeCall():List<Card>{
        var data = mutableListOf<Card>()
        AsyncTask.execute(){
            CoroutineScope(Dispatchers.Main).launch {

                try {
                    val response = ApiClientCards.apiService.getAllCards()

                    if (response.isSuccessful && response.body() != null) {
                        val content = response.body()
                        if (content != null) {
                            for (v in content){
                                data.add(v)
                            }
                            //data=content
                            Toast.makeText(
                                    activity,
                                    "prix: ${content[0].paymentId}",

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