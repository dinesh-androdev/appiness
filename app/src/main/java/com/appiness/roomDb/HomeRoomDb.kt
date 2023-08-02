package com.appiness.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appiness.homeScreen.HomeListModelItem

@Database(entities = [HomeListModelItem::class], version = 1, exportSchema = false)
abstract class HomeRoomDb : RoomDatabase() {
    abstract fun roomDao(): RoomDao
}