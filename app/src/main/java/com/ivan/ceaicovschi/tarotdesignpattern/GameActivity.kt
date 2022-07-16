package com.ivan.ceaicovschi.tarotdesignpattern

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val intent = intent
        val args = intent.getBundleExtra("args")
        val playerNames = args!!.getSerializable("players") as ArrayList<*>



        val btn_gameover: Button = findViewById<Button>(R.id.buttonfinish)

        val name_player_one: TextView = findViewById(R.id.playerone)
        val name_player_two: TextView = findViewById(R.id.playertwo)
        val name_player_three: TextView = findViewById(R.id.playerthree)
        val name_player_four: TextView = findViewById(R.id.playerfour)

        val player_names = arrayListOf<TextView>(
                name_player_one,
                name_player_two,
                name_player_three,
                name_player_four)

        for ((count, textField) in player_names.withIndex()) {
            textField.text = playerNames[count].toString()
        }

        btn_gameover.setOnClickListener {view ->
            val intent = Intent(view.context, SaveScoreActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}