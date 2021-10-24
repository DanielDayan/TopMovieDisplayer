package com.example.topmoviedisplayer

import android.os.Bundle
import android.view.MenuItem

import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import com.bumptech.glide.Glide
import com.example.topmoviedisplayer.util.SingleMovie
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.view.Menu


class MovieDetailsActivity : AppCompatActivity () {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_movie_details)
        // get selected movie item
        val movie = intent.getSerializableExtra("Selected_Movie") as SingleMovie
        // set title of action bar to movie title
        title = movie.title
        findViewById<TextView>(R.id.overview).text = movie.overview
        findViewById<TextView>(R.id.rating).text = movie.vote_average
        findViewById<TextView>(R.id.release_date).text = movie.release_date
        // go back to main activity - action bar button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Glide.with(applicationContext).load(movie.poster_path).into(findViewById(R.id.movie_image))


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //  check if pressed back
        if(item.itemId == android.R.id.home){
            // finish activity - goes back to MainActivity
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       return true
    }

}