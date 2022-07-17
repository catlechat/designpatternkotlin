package com.ivan.ceaicovschi.tarotdesignpattern

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newGameButton: Button = findViewById<View>(R.id.newGameButton) as Button
        newGameButton.setOnClickListener { view ->
            val intent = Intent(view.context, GameSetupActivity::class.java)
            view.context.startActivity(intent)
        }

        val loadGameButton: Button = findViewById<View>(R.id.loadGameButtoin) as Button
        loadGameButton.setOnClickListener { view ->
            val intent = Intent(view.context, LoadGameActivity::class.java)
            view.context.startActivity(intent)
        }
    }

}