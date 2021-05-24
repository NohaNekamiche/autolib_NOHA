package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.R

private var mylist= emptyList<Vehicle>()

class MyAdapter(val context: Context, var data: MutableLiveData<Vehicle>): RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.car_elt, parent, false))
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(data[position].image).into(holder.img);

        holder.marque.text=data[position].vehiclemodel
        holder.prix.text= data[position].unitpriceperhour.toString()+"DA/Hr"
        val car=data[position]
        holder.details.setOnClickListener { view->
            val  id=data[position].idVehicle
            val  img=data[position].image
            val  uni_hr=data[position].unitpriceperhour
            val  uni_jr=data[position].unitpriceperday
            val  model=data[position].vehiclemodel
            val vehiclebrand=data[position].vehiclebrand
            val bundle = bundleOf("id" to id,"img" to img,"model" to model, "hr" to uni_hr, "jr" to uni_jr ,
            "brand" to vehiclebrand)
            view?.findNavController()?.navigate(R.id.action_nav_home_to_nav_listcar,bundle)





        }
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



class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
fun setData(newList :List<Vehicle>){
mylist=newList


}