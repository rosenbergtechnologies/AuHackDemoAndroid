package com.rosenbergtech.auhackdemo.viewholders

import android.graphics.Color
import android.view.View
import com.rosenbergtech.auhackdemo.models.Post
import kotlinx.android.synthetic.main.post_row.view.*
import kotlinx.android.synthetic.main.timestamp_bar.view.*
import org.jetbrains.anko.backgroundColor
import org.joda.time.DateTime
import java.util.*

class PostRow(var mainRow: View, var post: Post) {

    init {
        initializeViews()
    }

    private fun initializeViews() {
        mainRow.survey_question_timestamp_tv.text = formatTimestamp(post.getTimestampLong())
        mainRow.post_tv.text = post.text
        mainRow.backgroundColor = Color.parseColor(getRandomBackgroundColor())

    }

    private fun formatTimestamp(timestamp: Long) : String {

        val minutesSincePostCreated = (DateTime.now().millis - timestamp) / 60000

        if (minutesSincePostCreated < 1) {
            return "A moment ago"
        }

        var postFix = " minute"
        if (minutesSincePostCreated > 1) postFix += "s"
        postFix += " ago"

        return minutesSincePostCreated.toString() + postFix
    }


    private fun getRandomBackgroundColor() : String {
        val colors = listOf("#673AB7", "#1976D2", "#E53935", "#6A1B9A", "#43A047", "#D84315", "#455A64", "#0097A7", "#D81B60")
        val randomIndex = getRandomNumber(0, colors.size)
        return colors.get(randomIndex)
    }

    private fun getRandomNumber(from: Int, to: Int) : Int {
        return Random().nextInt(to - from) + from
    }
}