package com.example.topmoviedisplayer.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.topmoviedisplayer.MovieDetailsActivity
import com.example.topmoviedisplayer.R

/*
    MyAdapter
        RecycleView custom Adapter holding movie cells items
 */

class MyAdapter(private val mContext: Context, private val mData: List<SingleMovie>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.movie_cell,parent,false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        // path of movie poster image
        val path = mData[position].poster_path
        holder.name?.text = mData[position].title
        // load image using glide
        Glide.with(mContext).load(path).into(holder.img!!)

    }
    override fun getItemCount(): Int {
       return mData.size
    }
    /*
        innder class MyViewHolder - RecycleView custom view holder
     */
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView? = null
        var img: ImageView? = null

        //
        init {
            name = itemView.findViewById(R.id.movie_name)
            img = itemView.findViewById(R.id.img_src)
            val button = itemView.findViewById<View>(R.id.extra_info)
            button.setOnClickListener { view ->
                // set on click listener for "More info" button
                val intent = Intent(view.context, MovieDetailsActivity::class.java)
                intent.putExtra("Selected_Movie", mData[adapterPosition])
                // start second activity with "Selected Movie"
                startActivity(view.context, intent, null)
            }
        }

    }
}