package com.ivan.ceaicovschi.tarotdesignpattern

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class GameSetupActivity : AppCompatActivity() {
    private var playersNames = emptyList<TextInputEditText>()
    private val  presenter = GameSetupViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_setup)
        val player1Name: TextInputEditText =
            findViewById<View>(R.id.player1Name) as TextInputEditText
        val player2Name: TextInputEditText =
            findViewById<View>(R.id.player2Name) as TextInputEditText
        val player3Name: TextInputEditText =
            findViewById<View>(R.id.player3Name) as TextInputEditText
        val player4Name: TextInputEditText =
            findViewById<View>(R.id.player4Name) as TextInputEditText
        playersNames = listOf(player1Name, player2Name, player3Name, player4Name)
        //read only, fix-size
        val radioGroup: RadioGroup = findViewById<View>(R.id.radioGroup1) as RadioGroup


        //default value
        twoPlayersMod()
        radioGroup.setOnCheckedChangeListener { group, id ->
            when (group.indexOfChild(group.findViewById(id))) {
                0 -> presenter.onPlayerChange(GameSetupViewModel.PlayerCount.two)
                1 -> presenter.onPlayerChange(GameSetupViewModel.PlayerCount.three)
                2 -> presenter.onPlayerChange(GameSetupViewModel.PlayerCount.four)

            }
        }
        val okClick = findViewById<Button>(R.id.loadGameButtoin)
        okClick.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            presenter.onValidate(playersNames.map { textInput -> textInput.text.toString()  })
        }




    }

    fun twoPlayersMod() {
        val arr = playersNames
        arr[0].visibility = View.VISIBLE;
        arr[1].visibility = View.VISIBLE;
        arr[2].visibility = View.INVISIBLE;
        arr[3].visibility = View.INVISIBLE;
    }

    fun threePlayersMod() {
        val arr = playersNames
        arr[0].visibility = View.VISIBLE;
        arr[1].visibility = View.VISIBLE;
        arr[2].visibility = View.VISIBLE;
        arr[3].visibility = View.INVISIBLE;
    }

    fun fourPlayersMod() {
        val arr = playersNames
        arr[0].visibility = View.VISIBLE;
        arr[1].visibility = View.VISIBLE;
        arr[2].visibility = View.VISIBLE;
        arr[3].visibility = View.VISIBLE;
    }

    fun goToNextScreen() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)


    }
}