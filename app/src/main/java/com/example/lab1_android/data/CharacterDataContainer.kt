package com.example.lab1_android.data

data class CharacterDataContainer(
    var offset: Int? = null,
    var limit: Int? = null,
    var total: Int? = null,
    var count: Int? = null,
    var results: List<SingleCharacter>? = null
)
