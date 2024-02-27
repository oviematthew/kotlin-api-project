package com.example.kotlin_api_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_api_project.R
import com.example.kotlin_api_project.model.Businesses

class BusinessAdapter(private val businessList: List<Businesses>) :
    RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder>() {
    inner class BusinessViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView
        var textName: TextView
        var textAddress: TextView
        var textPhone: TextView
        var textStatus: TextView
        var textRating: TextView


        init {
            //find views from xml file
            imageView = view.findViewById(R.id.imageView)
            textName = view.findViewById(R.id.textName)
            textAddress = view.findViewById(R.id.textAddress)
            textPhone = view.findViewById(R.id.textPhone)
            textStatus = view.findViewById(R.id.textStatus)
            textRating = view.findViewById(R.id.textRating)
        }
    }

    //set view to xml file created, and inflate
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.business_recycler, parent, false)
        return BusinessViewHolder(view)
    }


    //return a count of all items in the list
    override fun getItemCount(): Int {
        return businessList.size
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        val business = businessList[position]

        holder.textName.text = business.name
        //holder.textAddress.text = business.location.displayAddress
        holder.textPhone.text = business.phone
        holder.textStatus.text = if (business.isClosed) "Closed" else "Opened"
        holder.textRating.text = "Rating: ${business.rating}"


    }




}
