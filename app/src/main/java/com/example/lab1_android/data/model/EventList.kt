package com.example.lab1_android.data.model

data class EventList (
    var available: Int? = null,
    var returned: Int? = null,
    var collectionURI: String? = null,
    var items: List<EventSummary>? = null
)