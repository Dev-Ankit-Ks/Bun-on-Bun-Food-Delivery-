package com.example.bunonbun.Room

import androidx.room.*
import com.example.bunonbun.Models.MainMenuItem

@Dao
interface MainMenuItemDao {
    @Query("SELECT * FROM main_menu_items")
    fun getAll(): List<MainMenuItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<MainMenuItem>)

    @Delete
    fun delete(item: MainMenuItem)
}
