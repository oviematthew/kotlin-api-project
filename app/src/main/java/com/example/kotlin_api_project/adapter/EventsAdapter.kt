package com.example.kotlin_api_project.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_api_project.R
import com.example.kotlin_api_project.model.Event
import com.squareup.picasso.Picasso
import com.example.kotlin_api_project.databinding.ItemEventBinding

class EventsAdapter(var events: List<Event>) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    init {
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemEventBinding.bind(itemView)

        fun bind(event: Event) {
            Picasso.get().load(event.imageUrl).placeholder(R.drawable.baseline_calendar_month_24).into(binding.imageView)
            binding.eventNameTextView.text = event.name

            if (event.location.displayAddress != null) {
                binding.textAddress.text = event.location.displayAddress.joinToString()
            } else {
                binding.textAddress.text = "Address not available"
            }

            binding.textEventDescription.text = event.description
            binding.textAttendees.text = "Attendees: ${event.attendingCount.toString()}"
            binding.textIsFree.text = if (event.isFree) "Free" else "Paid"
        }
    }
}
