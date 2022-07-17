package com.ivan.ceaicovschi.tarotdesignpattern

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao{
    @Query("SELECT * FROM Game ORDER BY date DESC")
    fun getAll(): List<Game>

    @Insert
    fun insertGame(vararg game: Game)

    @Delete
    fun delete(game: Game)
}