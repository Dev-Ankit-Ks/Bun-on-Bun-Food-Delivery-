package com.example.bunonbun.Room

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bunonbun.Models.MainMenuItem

@Database(entities = [MainMenuItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainMenuItemDao(): MainMenuItemDao
}
