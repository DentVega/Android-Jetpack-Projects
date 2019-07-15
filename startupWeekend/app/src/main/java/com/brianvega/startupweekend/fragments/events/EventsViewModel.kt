package com.brianvega.startupweekend.fragments.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.brianvega.startupweekend.models.Event

class EventsViewModel : ViewModel() {

    private var repository: EventsRepository = EventsRepository()
    var events: LiveData<MutableList<Event>>? = null

    init {
        events = getEventsFirebase()
    }

    private fun getEventsFirebase(): LiveData<MutableList<Event>>? = repository.getEvents()

}
