package com.ivan.ceaicovschi.tarotdesignpattern

import androidx.lifecycle.ViewModel

class GameSetupViewModel(private val view: GameSetupActivity) : ViewModel() {

    private var countPlayer : NumberOfPlayer = NumberOfPlayer.Two

    enum class NumberOfPlayer(val value: Int) {
        Two(2),
        Three(3),
        Four(4)
    }

    fun onPlayerChange(countPlayer: NumberOfPlayer) {
        this.countPlayer = countPlayer
        when (countPlayer) {
            NumberOfPlayer.Two -> view.twoPlayersMod()
            NumberOfPlayer.Three -> view.threePlayersMod()
            NumberOfPlayer.Four -> view.fourPlayersMod()
        }
    }

    fun onValidate(names: List<String>) {

        if (names.take(countPlayer.value).any {
                it.isEmpty()
        }) {
            view.showError()
        } else {
            view.goToNextScreen()
        }
    }
}
