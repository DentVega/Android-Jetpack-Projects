package com.brianvega.startupweekend.fragments.events

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.brianvega.startupweekend.Constants

import com.brianvega.startupweekend.R
import com.brianvega.startupweekend.dialogFragments.eventDetail.EventDetail
import com.brianvega.startupweekend.fragments.BaseFragment
import com.brianvega.startupweekend.models.Event
import kotlinx.android.synthetic.main.events_fragment.*

class EventsFragment : BaseFragment() {

    var eventRecyclerView: EventsRecylerView? = null

    companion object {
        fun newInstance() = EventsFragment()
    }

    private lateinit var viewModel: EventsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.events_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showProgressBar(progress_bar_events, activity!!)
        viewModel = ViewModelProviders.of(this).get(EventsViewModel::class.java)
        viewModel.events!!.observe(this, Observer { events ->
            if (!events.isNullOrEmpty()) {
                loadRecyclerViewEvent(events)
            }
        })
    }

    private fun loadRecyclerViewEvent(events: List<Event>) {
        eventRecyclerView = EventsRecylerView(activity!!.applicationContext) { event ->
            showDetailEvent(event)
        }
        event_rv.adapter = eventRecyclerView
        event_rv.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        eventRecyclerView!!.setEvents(events)
        hideProgressBar(progress_bar_events, activity!!)
    }

    private fun showDetailEvent(event: Event) {
        val eventDetail: EventDetail = EventDetail.getInstance(event)
        eventDetail.show(activity!!.supportFragmentManager, Constants.DIALOG_FRAGMENT)
    }

}
