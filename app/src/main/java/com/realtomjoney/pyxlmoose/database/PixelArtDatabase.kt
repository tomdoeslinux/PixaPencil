package com.realtomjoney.pyxlmoose.database

import android.content.Context
import androidx.room.*
import androidx.room.Database
import com.realtomjoney.pyxlmoose.dao.PixelArtCreationsDao
import com.realtomjoney.pyxlmoose.models.PixelArt

@Database(entities = [PixelArt::class], version = 1)
abstract class PixelArtDatabase: RoomDatabase() {
    abstract fun pixelArtCreationsDao(): PixelArtCreationsDao

    companion object {
        private var instance: PixelArtDatabase? = null
        fun getDatabase(context: Context): PixelArtDatabase {
            if (instance == null) {
                synchronized(PixelArtDatabase::class) {
                    if (instance == null) instance = Room.databaseBuilder(context.applicationContext, PixelArtDatabase::class.java, AppData.dbFileName).allowMainThreadQueries().build()
                }
            }
            return instance!!
        }
    }
}