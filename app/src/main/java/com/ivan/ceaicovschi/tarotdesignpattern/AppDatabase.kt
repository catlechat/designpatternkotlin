package com.ivan.ceaicovschi.tarotdesignpattern

import androidx.room.*

@Database(entities = [Game::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}


