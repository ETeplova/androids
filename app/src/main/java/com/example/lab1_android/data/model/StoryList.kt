package com.example.lab1_android.data.model

import com.example.lab1_android.data.model.SeriesSummary

data class StoryList (
    var available: Int? = null,
    var returned: Int? = null,
    var collectionURI: String? = null,
    var items: List<SeriesSummary>? = null
)