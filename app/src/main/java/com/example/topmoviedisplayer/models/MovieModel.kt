package com.example.topmoviedisplayer.models

import android.util.Log
import com.example.topmoviedisplayer.util.SingleMovie
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import java.net.MalformedURLException
import java.net.URL


// poster base url : https://www.themoviedb.org/t/p/w220_and_h330_face
// movie discover : https://api.themoviedb.org/3/discover/movie?api_key=39ae52d2f8262ad6830a3caba9cc0f22&language=en-US&sort_by=popularity.desc&include_video=false&page=1

// base url adding to poster_path
const val BASE_URL = "https://www.themoviedb.org/t/p/w220_and_h330_face"
// url for json object
var JSON_URL = "https://api.themoviedb.org/3/discover/movie?api_key=39ae52d2f8262ad6830a3caba9cc0f22&language=en-US&sort_by=popularity.desc&page="

/*
    MovieModel
        app main model , handles the IO/Network request to TMDb and parsing the json.
 */
class MovieModel  {

    private var list: ArrayList<SingleMovie> = ArrayList()
    // current page
    private var pageNum = 1

    fun setPage(page:Int)  {
        pageNum = page
    }
    // get the data from JSON_URL by page num
     fun getData(): String {
        val url = JSON_URL + pageNum.toString()
        //println(url)
        Log.i("Model", "Got the Data")
        return fetchData(url)


    }

    /* helper function for getData
        establish network connection in a new thread and wait for the result
        return string of json object
    */
    private fun fetchData(curr_URL: String):String {
       var apiResponse : String = ""
            val t = Thread {
            try {
                apiResponse = URL(curr_URL).readText()
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
        }
        t.start()
        t.join()
        return apiResponse
    }
    /*
          process the date coming from getData function
          parsing the JSON object string and creating the SingleMovie objects and them to arraylist
     */
      fun processData(result: String) {
         val temp = ArrayList<SingleMovie>()
             try {

                 val jsonObj = JSONObject(result)
                 val jsonArr = jsonObj.getJSONArray("results")

                 for (i in 0 until jsonArr.length()) {
                     val item = jsonArr.getJSONObject(i)

                     val movieItem = SingleMovie(
                        BASE_URL + item.getString("poster_path"),
                         item.getString("release_date"),
                         item.getString("vote_average"),
                         item.getString("overview"),
                         item.getString("title")
                     )
                        temp.add(movieItem)
                 }
                 list.addAll(temp)
                 Log.i("JSON", "done")
             } catch (e: JSONException) { e.printStackTrace() }

    }

    fun getMovieList(): ArrayList<SingleMovie> {
        return list
    }


}













