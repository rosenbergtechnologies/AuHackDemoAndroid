package com.rosenbergtech.auhackdemo.models

import com.google.firebase.database.Exclude

/**
 * Created by JUJ on 09/04/2018.
 */
data class Post (
    var id: String = "",
    val text: String = "",
    var timestamp: HashMap<String, Any> = hashMapOf() ) {

    init {

    }

    @Exclude
    fun getTimestampLong(): Long {
        return timestamp.get("timestamp") as Long
    }
}