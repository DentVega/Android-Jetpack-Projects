package com.brianvega.startupweekend.firebase

enum class FirestoreCollection(collectionPath: String) {
    events("events"),
    sponsors("sponsors"),
    partners("partners"),
    metrics("metrics")
}