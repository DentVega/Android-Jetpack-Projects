package com.brianvega.startupweekend.models

interface DataType {
    var id: String
}

enum class DataOrder(val value: String) {
    asc("asc"),
    desc("desc")
}