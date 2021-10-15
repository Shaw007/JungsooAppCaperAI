package com.srmstudios.jungsoomarket.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.srmstudios.jungsoomarket.data.database.JungsooDatabase
import com.srmstudios.jungsoomarket.data.database.JungsooDatabaseCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatasourceModule {

    @Singleton
    @Provides
    fun provideJungsooDatabase(
        @ApplicationContext appContext: Context,
        jungsooDatabaseCallback: JungsooDatabaseCallback
    ): JungsooDatabase {
        return Room.databaseBuilder(
            appContext,
            JungsooDatabase::class.java,
            "db_jungsoo")
            .addCallback(jungsooDatabaseCallback)
            .build()
    }

    @Singleton
    @Provides
    fun provideAppCoroutineScope() = CoroutineScope(SupervisorJob() + Dispatchers.IO)
}