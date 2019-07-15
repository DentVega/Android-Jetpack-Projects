package com.brianvega.startupweekend.fragments.sponsors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brianvega.startupweekend.R
import com.brianvega.startupweekend.models.Sponsor
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_sponsor.view.*

class SponsorsRecyclerView internal constructor(context: Context): RecyclerView.Adapter<SponsorsRecyclerView.SponsorsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var sponsors: List<Sponsor> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorsViewHolder {
        val itemView = inflater.inflate(R.layout.item_sponsor, parent, false)
        return SponsorsViewHolder(itemView)
    }

    override fun getItemCount(): Int = sponsors.size

    override fun onBindViewHolder(holder: SponsorsViewHolder, position: Int) {
        holder.bind(sponsors[position])
    }

    internal fun setSponsors(sponsors: List<Sponsor>) {
        this.sponsors = sponsors
    }

    inner class SponsorsViewHolder(item: View): RecyclerView.ViewHolder(item) {

        fun bind(sponsor: Sponsor) = with(itemView) {
            Glide.with(context).load(sponsor.logoUrl).into(img_sponsor)
        }

    }

}