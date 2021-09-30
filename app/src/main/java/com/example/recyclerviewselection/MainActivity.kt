package com.example.recyclerviewselectiontoast

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        fun getContext() : Context {
            return this.getContext()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this)

        // Create data source -------------------------------
        val frankData = ArrayList<String>()      // create empty ArrayList
        createData(frankData, 50)           // populate it

        // Attach your RecyclerView Adapter to the RecyclerView instance
        val adapter = FrankRVAdapter(frankData)
        rv.adapter = adapter
    }

    /*
     createData() - create a data source (of size num) with random names taken from a static list
     */
    fun createData(mydata: ArrayList<String>, num: Int) {
        var s : String
        val names = arrayListOf<String>()
        names.addAll(listOf("Frank Greco", "Miles Davis", "Louie Armstrong", "Mick Jagger", "BB King", "Duane Allman", "Aretha Franklin"))

        for(i in 0..num)  {
            s = names.get(Random.nextInt(0, names.size)) + " - $i"
            mydata.add(s)
            Log.i("Frank", "name [" + s + "]")
        }
    }
}