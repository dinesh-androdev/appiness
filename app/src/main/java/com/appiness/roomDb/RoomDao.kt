package com.appiness.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appiness.homeScreen.HomeListModelItem

@Dao
interface RoomDao{

    @Query("SELECT * FROM home_list_model_item ORDER BY currentTime DESC")
    fun getAllMessages(): List<HomeListModelItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: HomeListModelItem)
}
