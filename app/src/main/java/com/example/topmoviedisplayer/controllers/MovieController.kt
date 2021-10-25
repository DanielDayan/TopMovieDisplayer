package com.example.topmoviedisplayer.controllers

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmoviedisplayer.models.MovieModel
import com.example.topmoviedisplayer.util.MyAdapter
import com.example.topmoviedisplayer.util.SingleMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/*
 ViewModel class
 model - movie displayer main model
 isLoading - true of view model loading more item to recycler view
 page - current page to get from TMDB
 */
class MovieController : ViewModel() {
    private val model: MovieModel = MovieModel()
    private var isLoading = false
    private var page: Int = 1

    // fetching data from model
      fun fetchData() {
          val s  = model.getData()
          viewModelScope.launch (Dispatchers.Default){
              model.processData(s)
          }
    }
    fun getMovieList(): ArrayList<SingleMovie> {
        return model.getMovieList()
    }
    fun isLoading(): Boolean {
        return isLoading
    }
    fun incPage() {
        page++
        model.setPage(page)
    }
    fun getPage() :Int {
        return page
    }
// get next page and notify the adapter
@SuppressLint("NotifyDataSetChanged")
 fun getNextPage(adapter: MyAdapter) {

        viewModelScope.launch {
            isLoading = true
            fetchData()
            delay(200)
            adapter.notifyDataSetChanged()
            isLoading = false

        }
    }
}