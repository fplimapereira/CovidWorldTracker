package com.flpereira.covidworldtracker.di

import android.content.Context
import androidx.room.Room
import com.flpereira.covidworldtracker.room.WorldCasesDataDao
import com.flpereira.covidworldtracker.room.WorldTrackerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideWorldTrackerDb(@ApplicationContext context: Context): WorldTrackerDatabase{
        return Room.databaseBuilder(
            context,
            WorldTrackerDatabase::class.java,
            WorldTrackerDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWorldCasesDataDao(worldTrackerDb: WorldTrackerDatabase): WorldCasesDataDao{
        return worldTrackerDb.worldCasesDao()
    }
}