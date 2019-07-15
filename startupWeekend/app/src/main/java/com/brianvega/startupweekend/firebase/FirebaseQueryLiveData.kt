package com.brianvega.startupweekend.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class FirebaseQueryLiveData {

    var db = FirebaseFirestore.getInstance()

    fun getData(collectionPath: String, data: (Task<QuerySnapshot>) -> Unit) {
        db.collection(collectionPath).get()
            .addOnCompleteListener {
                data(it)
        }
    }

}