package com.brianvega.startupweekend.fragments.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.brianvega.startupweekend.models.Event

class AddressViewModel: ViewModel() {

    private var repository: AddressRepository = AddressRepository()
    var events: LiveData<MutableList<Event>>? = null

    init {
        events = getEventsFirebase()
    }

    private fun getEventsFirebase(): LiveData<MutableList<Event>>? = repository.getEvents()

}