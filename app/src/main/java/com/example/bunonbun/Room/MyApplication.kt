package com.example.bunonbun.Room

import android.app.Application
import androidx.room.Room

class MyApplication : Application() {
    companion object {
       var db: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my-database"
        ).build()
    }
}
