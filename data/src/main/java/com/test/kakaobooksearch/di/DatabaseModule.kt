package com.test.kakaobooksearch.di

import android.content.Context
import androidx.room.Room
import com.test.kakaobooksearch.local.AppDatabase
import com.test.kakaobooksearch.local.DocumentDao
import com.test.kakaobooksearch.local.MetaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "KAKO_BOOK_DATABASE"
        ).build()

    @Provides
    fun provideDocumentDao(database: AppDatabase): DocumentDao = database.documentDao()

    @Provides
    fun provideMetaDao(database: AppDatabase): MetaDao = database.metaDao()
}
