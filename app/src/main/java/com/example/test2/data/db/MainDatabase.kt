package com.example.test2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test2.data.db.history.HistoryItemDao
import com.example.test2.data.db.history.HistoryItemEntity
import com.example.test2.data.db.typeConverters.LocalDateTimeConverter

@Database(
    entities = [HistoryItemEntity::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class MainDatabase: RoomDatabase() {

    abstract val historyItemDao: HistoryItemDao

    companion object {
        fun create(context: Context): MainDatabase =
            Room.databaseBuilder(context, MainDatabase::class.java, "main_database")
                .addMigrations(MigrationFrom1To2())
                .build()
    }
}

