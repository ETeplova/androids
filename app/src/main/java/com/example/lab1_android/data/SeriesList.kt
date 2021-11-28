package com.example.lab1_android.data

data class SeriesList (
    var available: Int? = null,
    var returned: Int? = null,
    var collectionURI: String? = null,
    var items: List<SeriesSummary>? = null
)