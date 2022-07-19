package com.ivan.ceaicovschi.tarotdesignpattern

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class GameOverActivity : AppCompatActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val game : Game = intent.getSerializableExtra("game") as Game
        val oldGame = game

        val players = arrayListOf<PlayerResult>(
            game.player1,
            game.player2,
        )

        if(game.player3 != null){
            players.add(game.player3!!)
        }

        if(game.player4 != null){
            players.add(game.player4!!)
        }

        val byScore = Comparator.comparing { obj: PlayerResult -> obj.score }
        val byName = Comparator.comparing { obj: PlayerResult -> obj.name }
        players.sortWith(byScore.thenComparing(byName).reversed())


        val dateText = findViewById<TextView>(R.id.dateText)
        dateText.text = game.date

        val playerWin = findViewById<TextView>(R.id.playerWin)
        var text = "${players[0].name} with ${players[0].score}"

        playerWin.text = text
        val playerSecond = findViewById<TextView>(R.id.playerSecond)

        text = "${players[1].name} with ${players[1].score}"
        playerSecond.text = text

        if(game.player3 != null){
            val playerThird = findViewById<TextView>(R.id.playerThird)
            playerThird.isVisible = true
            text = "${players[2].name} with ${players[2].score}"
            playerThird.text = text
        }

        if(game.player4 != null){
            val playerFourth = findViewById<TextView>(R.id.playerLoser)
            playerFourth.isVisible = true
            text = "${players[3].name} with ${players[3].score}"
            playerFourth.text = text
        }

        val playAgain = findViewById<Button>(R.id.playAgainButton)
        playAgain.setOnClickListener(){
            val playersStringNames = arrayListOf(
                oldGame.player1.name,
                oldGame.player2.name)
            if(oldGame.player3?.name == null) {
                playersStringNames.add("")
            } else {
                playersStringNames.add(oldGame.player3?.name.toString())
            }

            if(oldGame.player4?.name == null) {
                playersStringNames.add("")
            } else {
                playersStringNames.add(oldGame.player4?.name.toString())
            }

            val intent = Intent(this, GameActivity::class.java)
            val args = Bundle()
            args.putSerializable("players", playersStringNames)
            intent.putExtra("args", args);
            startActivity(intent)
        }


        val newGame = findViewById<Button>(R.id.newGameButton)
        newGame.setOnClickListener(){
            val intent = Intent(this, GameSetupActivity::class.java)
            startActivity(intent)

        }
    }
}
