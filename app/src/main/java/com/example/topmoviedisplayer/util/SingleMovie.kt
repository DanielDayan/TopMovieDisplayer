package com.example.topmoviedisplayer.util

import java.io.Serializable

/*
    SingleMovie data class compatible to JSON from TDMB
 */

data class SingleMovie(
    //val adult: Boolean,
    //val backdrop_path: String,
    //val id: Int,
    //val original_language: String,
    //val original_title: String,
    //val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val vote_average: String,
     val overview: String,
    val title: String,
    //val video: Boolean,
    //val vote_count: Int
) : Serializable
