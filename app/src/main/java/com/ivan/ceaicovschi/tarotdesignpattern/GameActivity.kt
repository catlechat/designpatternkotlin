package com.ivan.ceaicovschi.tarotdesignpattern

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.room.Room
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val random = Random()

        val intent = intent
        val args = intent.getBundleExtra("args")
        val playerNames = args!!.getSerializable("players") as ArrayList<*>

        val thirdPlayer: LinearLayout = findViewById(R.id.thirdPlayer)
        val fourthPlayer: LinearLayout = findViewById(R.id.fourthPlayer)

        val btnSaveGame: Button = findViewById<Button>(R.id.saveGame)
        val btnGameOver: Button = findViewById<Button>(R.id.buttonfinish)




        val plusOne: Button = findViewById<Button>(R.id.buttonplusone)
        val plusTwo: Button = findViewById<Button>(R.id.buttonplustwo)
        val plusThree: Button = findViewById<Button>(R.id.buttonplusthree)
        val plusFour: Button = findViewById<Button>(R.id.buttonplusfour)
        val plusButtons = arrayListOf(
            plusOne,
            plusTwo,
            plusThree,
            plusFour
        )

        val minusTwo: Button = findViewById<Button>(R.id.buttonminustwo)
        val minusOne: Button = findViewById<Button>(R.id.buttonminusone)
        val minusThree: Button = findViewById<Button>(R.id.buttonminusthree)
        val minusFour: Button = findViewById<Button>(R.id.buttonminusfour)
        val minusButtons = arrayListOf(
            minusOne,
            minusTwo,
            minusThree,
            minusFour
        )

        val scoreOne: TextView = findViewById(R.id.scoreone)
        val scoreTwo: TextView = findViewById(R.id.scoretwo)
        val scoreThree: TextView = findViewById(R.id.scorethree)
        val scoreFour: TextView = findViewById(R.id.scorefour)
        val scores = arrayListOf(
            scoreOne,
            scoreTwo,
            scoreThree,
            scoreFour
        )

        val namePlayerOne: TextView = findViewById(R.id.playerone)
        val namePlayerTwo: TextView = findViewById(R.id.playertwo)
        val namePlayerThree: TextView = findViewById(R.id.playerthree)
        val namePlayerFour: TextView = findViewById(R.id.playerfour)
        val playerNamesFields = arrayListOf(
            namePlayerOne,
            namePlayerTwo,
            namePlayerThree,
            namePlayerFour)

        for ((count, textField) in playerNamesFields.withIndex()) {
            textField.text = playerNames[count].toString()
            if(count == 2 && textField.text != ""){
                thirdPlayer.isVisible = true
            }
            if(count == 3 && textField.text != ""){
                fourthPlayer.isVisible = true
            }

        }

        for ((i, plusButton) in plusButtons.withIndex()) {
            plusButton.setOnClickListener{
                val prevScore = Integer.parseInt(scores[i].text.toString()) + 1
                scores[i].text = prevScore.toString()
            }
        }

        for ((i, minusButton) in minusButtons.withIndex()) {
            minusButton.setOnClickListener{
                var prevScore = Integer.parseInt(scores[i].text.toString())
                if(prevScore > 0){
                    prevScore --
                    scores[i].text = prevScore.toString()
                }
            }
        }

        btnGameOver.setOnClickListener {view ->

            val over = Intent(view.context, GameOverActivity::class.java)
            over.putExtra("game", getGame(playerNames, scores));
            view.context.startActivity(over)
        }

        btnSaveGame.setOnClickListener{
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "tarot-db"
            ).allowMainThreadQueries().build()
            val gameDao = db.gameDao()
            gameDao.insertGame(getGame(playerNames, scores))

            val over = Intent(it.context, MainActivity::class.java)
            it.context.startActivity(over)

        }

    }
    private fun getGame(playerNames: ArrayList<*>, scores: ArrayList<TextView>) : Game {
        val id = System.currentTimeMillis()
        val game = Game(
            id,
            PlayerResult(
                id + 1,
                playerNames[0].toString(),
                scores[0].text.toString()
            ),
            PlayerResult(
                id + 2,
                playerNames[1].toString(),
                scores[1].text.toString()
            ),
            if (playerNames[2].toString() != "") {
                PlayerResult(
                    id + 3,
                    playerNames[2].toString(),
                    scores[2].text.toString()
                )
            } else {
                null
            },
            if (playerNames[3].toString() != "") {
                PlayerResult(
                    id + 4,
                    playerNames[3].toString(),
                    scores[3].text.toString()
                )
            } else {
                null
            },
            SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(Date())
        )
        return game
    }

}