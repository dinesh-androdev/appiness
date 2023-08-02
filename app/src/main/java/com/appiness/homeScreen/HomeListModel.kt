package com.appiness.homeScreen

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_list_model_item")
data class HomeListModelItem(
	@PrimaryKey
	val currentTime: Long? = null,
	val message: String? = null
)
