package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.DataClasses.Car
import com.clovertech.autolibdz.R


class MyAdapter (val context: Context, var data:List<Car>): RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.car_elt, parent, false))
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.img.setImageResource(data[position].img)
        holder.marque.text=data[position].marque
        holder.prix.text= data[position].prix.toString()+"DA/Hr"


    }

}


class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val marque=view.findViewById<TextView>(R.id.marque) as TextView
    val prix=view.findViewById<TextView>(R.id.prix) as TextView
    val img=view.findViewById<ImageView>(R.id.car_img) as ImageView



}