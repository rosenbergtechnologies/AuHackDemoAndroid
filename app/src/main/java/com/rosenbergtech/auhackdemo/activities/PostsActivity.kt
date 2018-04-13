package com.rosenbergtech.auhackdemo.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rosenbergtech.auhackdemo.R
import com.rosenbergtech.auhackdemo.adapters.PostAdapter
import kotlinx.android.synthetic.main.activity_posts.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class PostsActivity : AppCompatActivity() {

    private var postAdapter: PostAdapter = PostAdapter(this, emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        fab.setOnClickListener { view ->
            startActivity(intentFor<CreatePostActivity>("id" to 5).singleTop())
        }

        postAdapter.getAdapterChangedObservable().subscribe(
                { adapter ->
                    println("onNext" + adapter)
                    postsListView.adapter = adapter
                },
                { error ->
                    println("onError" + error)
                },
                { println("onComplete") }
        )
    }
}
