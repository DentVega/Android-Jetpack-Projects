package com.brianvega.startupweekend.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint

class Event {

    lateinit var id: String
    lateinit var name: String
    lateinit var link: String
    lateinit var place: String
    lateinit var logoUrl: String
    lateinit var descriptionEn: String
    lateinit var descriptionSp: String
    lateinit var date: Timestamp
    lateinit var location: GeoPoint
}