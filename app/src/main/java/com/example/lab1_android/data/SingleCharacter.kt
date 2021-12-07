package com.example.lab1_android.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "singleCharacter")
data class SingleCharacter (
    @PrimaryKey var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    //var modified: Date? = null,
    var resourceURI: String? = null,
    var urls: List<Url>? = null,
    var thumbnail: Image? = null,
    var comics: ComicList? = null,
    var stories: StoryList? = null,
    var series: SeriesList? = null
)