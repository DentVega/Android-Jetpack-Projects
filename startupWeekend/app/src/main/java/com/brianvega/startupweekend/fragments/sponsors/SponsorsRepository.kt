package com.brianvega.startupweekend.fragments.sponsors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brianvega.startupweekend.firebase.FirebaseQueryLiveData
import com.brianvega.startupweekend.firebase.FirestoreCollection
import com.brianvega.startupweekend.models.Sponsor
import com.google.firebase.firestore.QueryDocumentSnapshot

class SponsorsRepository {

    fun getSponsors(): LiveData<MutableList<Sponsor>> {
        val sponsors = mutableListOf<Sponsor>()
        val data = MutableLiveData<MutableList<Sponsor>>()
        FirebaseQueryLiveData().getData(FirestoreCollection.sponsors.name) { task ->
            if (task.isSuccessful) {
                for (document: QueryDocumentSnapshot in task.result!!) {
                    sponsors.add(document.toObject(Sponsor::class.java))
                }
                data.postValue(sponsors)
            }
        }
        return data
    }

}