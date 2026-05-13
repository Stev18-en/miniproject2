package com.stevenmarchy0013.simukmin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stevenmarchy0013.simukmin.model.Setoran
import com.stevenmarchy0013.simukmin.model.DeletedSetoran
@Database(
    entities = [
        Setoran::class,
        DeletedSetoran::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun setoranDao(): SetoranDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "setoran_db"

                )   .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}