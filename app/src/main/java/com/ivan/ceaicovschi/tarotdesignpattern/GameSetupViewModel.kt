package com.ivan.ceaicovschi.tarotdesignpattern

import android.content.Intent
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

class GameSetupViewModel(private val view: GameSetupActivity) {

    private var countPlayer : PlayerCount = PlayerCount.two


    enum class PlayerCount(val value: Int) {
        two(2),
        three(3),
        four(4)


    }


    fun onPlayerChange(countPlayer: PlayerCount) {
        this.countPlayer = countPlayer
        when (countPlayer) {
            PlayerCount.two -> view.twoPlayersMod()
            PlayerCount.three -> view.threePlayersMod()
            PlayerCount.four -> view.fourPlayersMod()

        }


    }

    fun onValidate(names: List<String>) {

        if (names.take(countPlayer.value).any {
                it.isEmpty()
            }) {
            //view.showError()
        } else {
            view.goToNextScreen()
        }


    }

}