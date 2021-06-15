package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.cardetails.CarDetailsFragment


class MyCarAdapter(val context: Context, var data: List<Vehicle>): RecyclerView.Adapter<MyCarAdapter.MyHolder>(),
        Filterable{

    var carsListFiltered: ArrayList<Vehicle> = ArrayList()

    private var onNothingFound: (() -> Unit)? = null
    fun setCarList(cars:List<Vehicle>){
        this.data=cars.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.car_elt, parent, false))
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Glide.with(context).load(data[position].image).into(holder.img);

        holder.marque.text=data[position].vehiclemodel
        holder.prix.text= data[position].unitpriceperhour.toString()+"DA/Hr"


        holder.details.setOnClickListener { v->
        val car=data[position]
        val  id=data[position].idVehicle
        val  img=data[position].image
        val  uni_hr=data[position].unitpriceperhour
        val  uni_jr=data[position].unitpriceperday
        val  model=data[position].vehiclemodel
        val vehiclebrand=data[position].vehiclebrand
        val bundle = bundleOf("id" to id,"img" to img,"model" to model, "hr" to uni_hr, "jr" to uni_jr ,
            "brand" to vehiclebrand)
        v?.findNavController()?.navigate(R.id.action_nav_home_to_nav_listcar,bundle)
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

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                carsListFiltered.clear()
                if (constraint.isNullOrBlank()) {
                    carsListFiltered.addAll(data)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                    for (item in 0..data.size) {
                        if (data[item].vehiclemodel!!.toLowerCase().contains(filterPattern) ||
                                data[item].vehiclebrand!!.toLowerCase().contains(filterPattern) ||
                                data[item].vehicletype!!.toLowerCase().contains(filterPattern)) {
                            carsListFiltered.add(data[item])
                        }
                    }
                }
                return filterResults.also {
                    it.values = carsListFiltered
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (carsListFiltered.isNullOrEmpty())
                    onNothingFound?.invoke()
                notifyDataSetChanged()

            }
        }
    }


   inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        val marque = view.findViewById<TextView>(R.id.marque) as TextView
        val prix = view.findViewById<TextView>(R.id.prix) as TextView
        val img = view.findViewById<ImageView>(R.id.car_img) as ImageView
        val details = view.findViewById<Button>(R.id.details) as Button

    }


}






/*private fun replaceFragment(fragment: Fragment) {
    supportFragmentManager
            .beginTransaction()
            .add(R.id.car_details, fragment)
            .commit()
}
*/
