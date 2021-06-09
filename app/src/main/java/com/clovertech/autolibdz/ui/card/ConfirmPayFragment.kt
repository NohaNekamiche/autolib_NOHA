package com.clovertech.autolibdz.ui.card

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.DataClass.Pay
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.MainViewModel
import com.clovertech.autolibdz.ViewModel.MainViewModelFactory
import com.clovertech.autolibdz.repository.PaymentRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_card.*
import kotlinx.android.synthetic.main.fragment_add_card.close
import kotlinx.android.synthetic.main.fragment_confirm_pay.*

class ConfirmPayFragment : BottomSheetDialogFragment() {


    private lateinit var viewModel : MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm_pay,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        close.setOnClickListener{
            this.dismiss()
        }
        cancel.setOnClickListener{
            this.dismiss()
        }
        last4.setText("xxx xxxx "+arguments?.getString("last4").toString())
        val repository = PaymentRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)
                .get(MainViewModel::class.java)
        confirm.setOnClickListener{
            val paymentId=arguments?.getString("paymentId").toString()
            val amount= arguments?.getString("amount").toString()
            val idRental= arguments?.getString("idRental").toString()
            val type=arguments?.getString("type").toString()
            println("paymentId is"+paymentId)
            //Toast.makeText(context,amount,Toast.LENGTH_LONG).show()
            val pay = Pay(paymentId,"1900","10",type)

            viewModel.pay(pay)
            viewModel.PayResponse.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    Log.e("Push", (response.body().toString()))
                    Log.e("Push", response.code().toString())
                    Log.e("Push", response.message())
                    Log.e("push",response.headers().toString())
                    Log.e("push",response.errorBody().toString())
                    Log.e("push",response.toString())
                    Log.e("push",response.raw().toString())
                    this.dismiss()

                    Toast.makeText(
                            context,
                            "Payment process successful yaay",
                            Toast.LENGTH_SHORT
                    ).show()


                }
                else {
                    Log.e("Push", response.body().toString())
                    Log.e("Push", response.code().toString())
                    Log.e("Push", response.message().toString())
                    Toast.makeText(
                            context,
                            "echec",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }


    }


}