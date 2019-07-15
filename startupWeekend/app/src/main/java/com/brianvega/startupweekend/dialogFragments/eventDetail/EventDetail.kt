package com.brianvega.startupweekend.dialogFragments.eventDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.brianvega.startupweekend.R
import com.brianvega.startupweekend.models.Event
import com.brianvega.startupweekend.utils.DateConvert
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.dialog_fragment_event_detail.view.*

class EventDetail: DialogFragment() {

    companion object {
        fun getInstance(event: Event): EventDetail {
            val f = EventDetail()
            f.event = event
            return f
        }
    }

    private lateinit var rootView: View
    private lateinit var event: Event

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.dialog_fragment_event_detail , container, false)
        loadReferences()
        return rootView
    }

    private fun loadReferences() {
        rootView.txt_title.text = event.name
        rootView.txt_address.text = DateConvert.timestampToString(event.date)
        rootView.txt_description.text = event.descriptionSp
        Glide.with(context!!).load(event.logoUrl).into(rootView.img_event)
    }

}