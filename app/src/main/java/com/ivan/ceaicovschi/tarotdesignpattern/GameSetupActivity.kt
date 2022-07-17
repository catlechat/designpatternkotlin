package com.ivan.ceaicovschi.tarotdesignpattern

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
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
    var playersStringNames = ArrayList<String>()

    private lateinit var player1Name: TextInputEditText
    private lateinit var player2Name: TextInputEditText
    private lateinit var player3Name: TextInputEditText
    private lateinit var player4Name: TextInputEditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewModelFactory = GameSetupActivityViewModelFactory(this)
        presenter = ViewModelProvider(this, this.viewModelFactory).get(GameSetupViewModel::class.java)

        setContentView(R.layout.activity_game_setup)
        player1Name = findViewById<View>(R.id.player1Name) as TextInputEditText
        player2Name = findViewById<View>(R.id.player2Name) as TextInputEditText
        player3Name = findViewById<View>(R.id.player3Name) as TextInputEditText
        player4Name = findViewById<View>(R.id.player4Name) as TextInputEditText

        playersNames = listOf(player1Name, player2Name, player3Name, player4Name)
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

        val okClick = findViewById<Button>(R.id.loadGameButton)
        okClick.setOnClickListener {
            playersStringNames = arrayListOf(
                player1Name.text.toString(),
                player2Name.text.toString(),
                player3Name.text.toString(),
                player4Name.text.toString())
            presenter.onValidate(playersStringNames)
        }

        val backClick = findViewById<Button>(R.id.backButton)
        backClick.setOnClickListener{
            goBackScreen()
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
        val args = Bundle()
        args.putSerializable("players", playersStringNames)
        intent.putExtra("args", args);

        startActivity(intent)
    }

    fun goBackScreen() {
        this.finish()
    }

    fun showError() {
        playersStringNames.forEachIndexed { index, name ->
            if(name == ""){
                playersNames[index].backgroundTintList = ColorStateList.valueOf(Color.RED )
                Log.d("machintruc", "Empty name on line " + (index+1) )
            }
        }

    }
}
