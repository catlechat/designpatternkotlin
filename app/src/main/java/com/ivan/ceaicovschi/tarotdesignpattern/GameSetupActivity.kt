package com.ivan.ceaicovschi.tarotdesignpattern

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText


class GameSetupActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: GameSetupActivityViewModelFactory
    private lateinit var presenter: GameSetupViewModel

    private var playersNames = emptyList<TextInputEditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewModelFactory = GameSetupActivityViewModelFactory(this)
        presenter = ViewModelProvider(this, this.viewModelFactory).get(GameSetupViewModel::class.java)

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
                0 -> presenter.onPlayerChange(GameSetupViewModel.NumberOfPlayer.Two)
                1 -> presenter.onPlayerChange(GameSetupViewModel.NumberOfPlayer.Three)
                2 -> presenter.onPlayerChange(GameSetupViewModel.NumberOfPlayer.Four)

            }
        }
        val okClick = findViewById<Button>(R.id.loadGameButtoin)
        okClick.setOnClickListener {
            val playersNames = arrayListOf(
                player1Name.text,
                player2Name.text,
                player3Name.text,
                player4Name.text)
            val intent = Intent(this, GameActivity::class.java)

            val args = Bundle()
            args.putSerializable("players", playersNames)
            intent.putExtra("args", args);

            startActivity(intent)
            presenter.onValidate(playersNames.map { textInput -> textInput.toString()  })
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

    fun showError() {
        Log.d("player name ", "the player name can't be empty")
    }
}
