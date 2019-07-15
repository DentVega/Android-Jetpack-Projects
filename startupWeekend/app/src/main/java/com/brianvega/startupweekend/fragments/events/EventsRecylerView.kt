package com.brianvega.startupweekend.fragments.events

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brianvega.startupweekend.R
import com.brianvega.startupweekend.models.Event
import com.brianvega.startupweekend.utils.DateConvert
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_event.view.*

class EventsRecylerView internal constructor(context: Context,
                                             private val showDetailEvent: (Event) -> Unit): RecyclerView.Adapter<EventsRecylerView.EventsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var events: List<Event> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val itemView = inflater.inflate(R.layout.item_event, parent, false)
        return EventsViewHolder(itemView)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(events[position])
    }

    internal fun setEvents(event :List<Event>) {
        events = event
    }

    inner class EventsViewHolder(item: View): RecyclerView.ViewHolder(item) {

        fun bind(event: Event) = with(itemView) {
            txt_title_event.text = event.name
            txt_date_event.text = DateConvert.timestampToString(event.date)
            txt_address_event.text = event.place
            Glide.with(context).load(event.logoUrl).into(img_icon_event)
            btn_read_more.setOnClickListener {
                showDetailEvent(event)
            }
        }
    }

}