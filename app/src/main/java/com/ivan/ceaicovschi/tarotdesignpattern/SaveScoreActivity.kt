package com.ivan.ceaicovschi.tarotdesignpattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SaveScoreActivity : AppCompatActivity() {
    private val itemsArray = ArrayList<SaveScore>()
    private lateinit var customAdapter: AdapteurSaveScore.CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_score)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        customAdapter = AdapteurSaveScore.CustomAdapter(itemsArray)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
        prepareItems()
    }

    private fun prepareItems() {
        val save1 = SaveScore("Player 1 Score 20", "Player 2 Score 10", "Player 3 Score 5", "Player 4 Score 0", "10/10/2022")
        itemsArray.add(save1)
        itemsArray.add(save1)
        itemsArray.add(save1)
        itemsArray.add(save1)
        itemsArray.add(save1)
        customAdapter.notifyDataSetChanged()
    }


}
