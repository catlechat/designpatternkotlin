package com.ivan.ceaicovschi.tarotdesignpattern

import androidx.room.*
import java.io.Serializable

@Entity
data class Game (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "player1") var player1: PlayerResult,
    @ColumnInfo(name = "player2") var player2: PlayerResult,
    @ColumnInfo(name = "player3") var player3: PlayerResult? = null,
    @ColumnInfo(name = "player4") var player4: PlayerResult? = null,
    @ColumnInfo(name = "date") var date: String
) : Serializable

@Entity
data class PlayerResult (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") var name : String,
    @ColumnInfo(name = "score") var score : String
) : Serializable




