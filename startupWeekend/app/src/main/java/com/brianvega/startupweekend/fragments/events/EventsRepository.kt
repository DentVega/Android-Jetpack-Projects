package com.brianvega.startupweekend.fragments.events

import androidx.lifecycle.LiveData
import com.brianvega.startupweekend.models.Event
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot


class EventsRepository {

    var db = FirebaseFirestore.getInstance()

    fun getEvents() {
        val events = mutableListOf<Event>()
        db.collection("events")
            .get()
            .addOnCompleteListener {    task: Task<QuerySnapshot> ->
                if (task.isSuccessful) {
                    for (document: QueryDocumentSnapshot in task.result!!) {
                        events.add(document.toObject(Event::class.java))
                    }
                } else {

                }
            }
    }

}