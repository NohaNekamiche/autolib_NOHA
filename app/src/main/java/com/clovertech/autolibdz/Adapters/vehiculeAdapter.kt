package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.cardetails.CarDetailsFragment


class MyCarAdapter(val context: Context, var data: List<Vehicle>): RecyclerView.Adapter<MyHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.car_elt, parent, false))
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        //Glide.with(context).load(data[position].image).into(holder.img);

        holder.marque.text=data[position].vehiclemodel
        holder.prix.text= data[position].unitpriceperhour.toString()+"DA/Hr"
        val car=data[position]
        /*  holder.details.setOnClickListener { v->
           //  val carDetailfragment = CarDetailsFragment()
              //replaceFragment(carDetailfragment)



              val intent=Intent(context,CarDetailsFragment::class.java).apply {
                //  putExtra("car",car)

              }
              context.startActivity(intent)
          }*/

    }



}

private fun ImageView.setImageResource(image: String) {

}


class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
    val marque=view.findViewById<TextView>(R.id.marque) as TextView
    val prix=view.findViewById<TextView>(R.id.prix) as TextView
    val img=view.findViewById<ImageView>(R.id.car_img) as ImageView
    val details=view.findViewById<Button>(R.id.details) as Button



}
/*private fun replaceFragment(fragment: Fragment) {
    supportFragmentManager
            .beginTransaction()
            .add(R.id.car_details, fragment)
            .commit()
}
*/
