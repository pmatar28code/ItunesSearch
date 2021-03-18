package com.example.itunessearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

/*
    import java.io.BufferedReader
    import java.io.InputStream
    import java.io.InputStreamReader
    import java.net.HttpURLConnection
    import java.net.URL

 */

    var queryMap = mutableMapOf<String,String>(
            "media" to "music",
            "entity" to "song",
            "term" to "pink+floyd+shine+on+you+crazy+diamond"
    )
    var url = "https://itunes.apple.com/search?"


    fun buildUrl(url:String,query:MutableMap<String,String>):String{
        var newUrl = url
        for((key,value)in query){
            newUrl +="${key}=${value}&"
        }
        newUrl = newUrl.dropLast(1)

        return newUrl
    }

//test
    println(buildUrl(url,queryMap))

    fun convertInputStreamToString(inputStream: InputStream): String {
        val bufferedReader: BufferedReader? = BufferedReader(InputStreamReader(inputStream))
        var line:String? = bufferedReader?.readLine()
        var result:String = ""

        while (line != null) {
            result += line
            line = bufferedReader?.readLine()
        }

        inputStream.close()
        return result
    }

    fun httpGet(myURL: String?): String {

        val inputStream: InputStream
        val result:String

        // create URL
        val url:URL = URL(myURL)

        // create HttpURLConnection
        val conn:HttpURLConnection = url.openConnection() as HttpURLConnection

        // make GET request to the given URL
        conn.connect()

        // receive response as inputStream
        inputStream = conn.inputStream

        // convert inputstream to string
        if(inputStream != null)
            result = convertInputStreamToString(inputStream)
        else
            result = "Did not work!"

        return result
    }

// call
    httpGet(buildUrl(url,queryMap))
}

