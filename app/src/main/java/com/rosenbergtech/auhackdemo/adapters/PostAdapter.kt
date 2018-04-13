package com.rosenbergtech.auhackdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.firebase.database.*
import com.rosenbergtech.auhackdemo.viewholders.PostRow
import com.rosenbergtech.auhackdemo.R
import com.rosenbergtech.auhackdemo.models.Post
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class PostAdapter(context: Context, posts: List<Post>): BaseAdapter() {

    private val adapterChangedObservable: Observable<PostAdapter>? = null
    private val mContext: Context = context
    private var posts: List<Post> = posts

    private val POSTS_PATH = "posts"
    private val firebaseRealtimeDatabase = FirebaseDatabase.getInstance()
    private val postsRef = firebaseRealtimeDatabase.getReference(POSTS_PATH)

    private lateinit var adapterChangedEmitter: ObservableEmitter<PostAdapter>

    init {
        connectAdapterToFirebase()
    }

    private fun connectAdapterToFirebase() {

        // Here we should make our adapter connect to Firebase!
    }

    private fun mapJsonToPosts(dataSnapshot: DataSnapshot) : List<Post> {

        val posts = arrayListOf<Post>()

        dataSnapshot.children.forEach {
            val post = it.getValue<Post>(Post::class.java)
            if(post != null) posts.add(post)
        }

        return posts
    }

    fun getAdapterChangedObservable() : Observable<PostAdapter> {
        return adapterChangedObservable ?: createAdapterChangedObservable()
    }

    //region View stuff
    // Responsible for rendering out each row
    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        val currentPost = getCurrentPost(position)
        val layoutInflater = LayoutInflater.from(mContext)

        var mainRow = layoutInflater.inflate(R.layout.post_row, viewGroup, false)
        var viewHolder = PostRow(mainRow, currentPost)

        return viewHolder.mainRow
    }

    // You can ignore this for now
    override fun getItem(position: Int): Any {
        return posts[position]
    }

    // You can also ignore this
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Responsible for how many rows in my list
    override fun getCount(): Int {
        return posts.size
    }

    private fun createAdapterChangedObservable(): Observable<PostAdapter> {

        return Observable.create { observer: ObservableEmitter<PostAdapter> ->
            this.adapterChangedEmitter = observer
        }
    }

    private fun adapterChanged() {
        if (adapterChangedEmitter != null) adapterChangedEmitter.onNext(this)
    }

    private fun getCurrentPost(position: Int): Post {
        return posts[position]
    }
    //endregion
}