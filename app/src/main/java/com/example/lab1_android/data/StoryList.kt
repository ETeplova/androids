package com.example.lab1_android.data

data class StoryList (
    var available: Int? = null,
    var returned: Int? = null,
    var collectionURI: String? = null,
    var items: List<SeriesSummary>? = null
)