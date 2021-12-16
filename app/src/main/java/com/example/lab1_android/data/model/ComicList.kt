package com.example.lab1_android.data.model

data class ComicList(
    var available: Int? = null,
    var optional: Int? = null,
    var collectionURI: String? = null,
    var items: List<ComicSummary>? = null
) {
}