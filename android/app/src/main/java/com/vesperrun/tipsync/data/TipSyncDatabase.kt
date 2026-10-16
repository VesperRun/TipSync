package com.vesperrun.tipsync.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [GigEntity::class, CastLineEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class TipSyncDatabase : RoomDatabase() {
    abstract fun gigDao(): GigDao

    companion object {
        @Volatile
        private var instance: TipSyncDatabase? = null

        fun get(context: Context): TipSyncDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    TipSyncDatabase::class.java,
                    "tipsync.db",
                ).build().also { instance = it }
            }
    }
}
