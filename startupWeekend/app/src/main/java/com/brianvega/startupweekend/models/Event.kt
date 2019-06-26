package com.brianvega.startupweekend.models

import com.google.firebase.Timestamp

class Event {

    lateinit var id: String
    lateinit var name: String
    lateinit var link: String
    lateinit var place: String
    lateinit var logoUrl: String
    lateinit var descriptionEn: String
    lateinit var descriptionSp: String
    lateinit var date: Timestamp

}