package com.appiness.di

import android.app.Application
import androidx.room.Room
import com.appiness.homeScreen.HomeRepository
import com.appiness.roomDb.HomeRoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRoomDb(app:Application): HomeRoomDb{
        return Room.databaseBuilder(
            app,
            HomeRoomDb::class.java, "home_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(homeRoomDb: HomeRoomDb):HomeRepository{
        return HomeRepository(homeRoomDb)
    }
}