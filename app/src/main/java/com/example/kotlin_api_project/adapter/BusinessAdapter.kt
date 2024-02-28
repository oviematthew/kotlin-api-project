package com.example.kotlin_api_project.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        val business = businessList[position]

        // Load image using Picasso
        Picasso.get().load(business.image_url).placeholder(R.drawable.baseline_apartment_24).into(holder.imageView)

        // Business name
        holder.textName.text = business.name

        // Check if displayAddress is not null before using joinToString
        val displayAddress = business.location.display_address
        if (displayAddress != null) {
            holder.textAddress.text = displayAddress.joinToString()
        } else {
            holder.textAddress.text = "Address not available"
        }

        // Display phone number
        holder.textPhone.text = business.phone

        // Set click listener for phone number
        holder.textPhone.setOnClickListener {
            // Handle phone number click, initiate a phone call
            val phoneNumber = business.phone
            if (phoneNumber.isNotBlank()) {
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                it.context.startActivity(dialIntent)
            }
        }

        holder.textStatus.text = if (business.isClosed) "Closed" else "Open"
        holder.textRating.text = "Rating: ${business.rating}"
    }

}
