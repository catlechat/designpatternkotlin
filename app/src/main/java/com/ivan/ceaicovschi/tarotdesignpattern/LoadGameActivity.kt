package com.ivan.ceaicovschi.tarotdesignpattern

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class LoadGameActivity : AppCompatActivity() {


    private var itemsArray : List<Game> = ArrayList()
    private lateinit var customAdapter: AdaptorLoadScore.CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_game)

        val backButton = findViewById<Button>(R.id.gamesBackButton)
        backButton.setOnClickListener{
            finish()
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        prepareGames()
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
    }


    private fun prepareGames() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "tarot-db"
        ).allowMainThreadQueries().build()
        val gameDao = db.gameDao()

        itemsArray = gameDao.getAll() as List<Game>
        customAdapter = AdaptorLoadScore.CustomAdapter(itemsArray)
        customAdapter.notifyDataSetChanged()
    }
}