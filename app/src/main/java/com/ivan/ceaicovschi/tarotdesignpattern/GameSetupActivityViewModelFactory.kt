package com.ivan.ceaicovschi.tarotdesignpattern

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameSetupActivityViewModelFactory(private val view: GameSetupActivity) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameSetupViewModel::class.java)){
            return GameSetupViewModel(view) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}
