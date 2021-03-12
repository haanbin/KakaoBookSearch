package com.test.kakaobooksearch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.kakaobooksearch.data.local.dto.DocumentDto
import com.test.kakaobooksearch.data.local.dto.MetaDto

@Database(entities = [DocumentDto::class, MetaDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun documentDao(): DocumentDao

    abstract fun metaDao(): MetaDao
}
