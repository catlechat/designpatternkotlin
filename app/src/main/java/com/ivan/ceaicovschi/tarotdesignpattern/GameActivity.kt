package com.ivan.ceaicovschi.tarotdesignpattern

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val btn_click_me: Button = findViewById(R.id.buttonfinish) as Button

        btn_click_me.setOnClickListener {view ->
            val intent = Intent(view.context, SaveScoreActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}