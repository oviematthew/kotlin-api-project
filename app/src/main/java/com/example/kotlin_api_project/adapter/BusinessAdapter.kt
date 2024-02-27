package com.example.kotlin_api_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_api_project.R
import com.example.kotlin_api_project.model.Businesses

class BusinessAdapter(var businessList: List<Businesses>) :
    RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder>() {

    inner class BusinessViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.imageView)
        var textName: TextView = view.findViewById(R.id.textName)
        var textAddress: TextView = view.findViewById(R.id.textAddress)
        var textPhone: TextView = view.findViewById(R.id.textPhone)
        var textStatus: TextView = view.findViewById(R.id.textStatus)
        var textRating: TextView = view.findViewById(R.id.textRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_business, parent, false)
        return BusinessViewHolder(view)
    }

    override fun getItemCount(): Int {
        return businessList.size
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        val business = businessList[position]

        holder.textName.text = business.name
        holder.textAddress.text = business.location.displayAddress.joinToString(separator = "\n")
        holder.textPhone.text = business.phone
        holder.textStatus.text = if (business.isClosed) "Closed" else "Opened"
        holder.textRating.text = "Rating: ${business.rating}"
    }
}
