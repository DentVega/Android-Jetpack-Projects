package com.brianvega.startupweekend.fragments.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brianvega.startupweekend.firebase.FirebaseQueryLiveData
import com.brianvega.startupweekend.firebase.FirestoreCollection
import com.brianvega.startupweekend.models.Event
import com.google.firebase.firestore.QueryDocumentSnapshot

class AddressRepository {

    fun getEvents(): LiveData<MutableList<Event>>? {
        val events = mutableListOf<Event>()
        val data = MutableLiveData<MutableList<Event>>()
        FirebaseQueryLiveData().getData(FirestoreCollection.events.name) { task ->
            if (task.isSuccessful) {
                for (document: QueryDocumentSnapshot in task.result!!) {
                    events.add(document.toObject(Event::class.java))
                }
                data.postValue(events)
            }
        }
        return data
    }

}