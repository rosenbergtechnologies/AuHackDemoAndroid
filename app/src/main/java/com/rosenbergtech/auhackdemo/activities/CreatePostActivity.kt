package com.rosenbergtech.auhackdemo.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.rosenbergtech.auhackdemo.R
import com.rosenbergtech.auhackdemo.models.Post

import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() {

    private val POSTS_PATH = "posts"
    private val firebaseRealtimeDatabase = FirebaseDatabase.getInstance()
    private val postsRef = firebaseRealtimeDatabase.getReference(POSTS_PATH)

    override fun onCreate(savedInstanceState: Bundle?) {

        //region You can ignore this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Don't show action title

        //endregion
    }

    private fun addPost(post: Post) {
        post.id = generateUniqueIdentifier()
        post.timestamp.put("timestamp", ServerValue.TIMESTAMP)
        addPostToFirebase(post)
    }

    private fun generateUniqueIdentifier(): String {
        return postsRef.push().key
    }

    private fun addPostToFirebase(post: Post) {
        postsRef.child(post.id).setValue(post)
    }

    //region Ignore this stuff
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_send -> {

                val post = Post(text = postEditText.text.toString())
                addPost(post)
                finish()

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
