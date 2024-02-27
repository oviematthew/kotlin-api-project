package com.example.kotlin_api_project.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_api_project.R
import com.example.kotlin_api_project.model.Businesses

class BusinessAdapter(private val context: Context, private val businessList: List<Businesses>) :
    RecyclerView.Adapter<BusinessAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.business_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val business = businessList[position]

        holder.textName.text = business.name
        holder.textAddress.text = business.location.displayAddress.joinToString("\n")
        holder.textPhone.text = business.phone
        holder.textStatus.text = if (business.isClosed) "Closed" else "Opened"
        holder.textRating.text = "Rating: ${business.rating}"


    }

    override fun getItemCount(): Int {
        return businessList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textName: TextView = itemView.findViewById(R.id.textName)
        val textAddress: TextView = itemView.findViewById(R.id.textAddress)
        val textPhone: TextView = itemView.findViewById(R.id.textPhone)
        val textStatus: TextView = itemView.findViewById(R.id.textStatus)
        val textRating: TextView = itemView.findViewById(R.id.textRating)
    }
}
