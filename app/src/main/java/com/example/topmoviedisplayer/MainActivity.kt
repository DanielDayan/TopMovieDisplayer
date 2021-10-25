package com.example.topmoviedisplayer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topmoviedisplayer.databinding.ActivityMainBinding
import com.example.topmoviedisplayer.controllers.MovieController
import com.example.topmoviedisplayer.util.MyAdapter


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var controller : MovieController
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //view model binding
        controller = ViewModelProvider(this).get(MovieController::class.java)
        setContentView(binding.root)
        recyclerView = findViewById(R.id.movieCells)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // OnScrollListener -  add on observer
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //check if vm loading data right now
                if (!controller.isLoading()) {
                    // detect scroll down end of lione
                    if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE)  {
                        Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT).show()
                        //get next page
                        controller.incPage()
                        controller.getNextPage(adapter)

                    }
                }
            }
        })
        // get 1st page on create
        controller.fetchData()
        updateDataOnRecyclerView()
    }

    // updates recycle view  first time app load
    private fun updateDataOnRecyclerView() {
        adapter = MyAdapter(this, controller.getMovieList())
        recyclerView.adapter = adapter
    }

}


