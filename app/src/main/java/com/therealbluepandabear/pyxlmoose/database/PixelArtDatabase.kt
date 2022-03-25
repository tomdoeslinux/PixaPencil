package com.therealbluepandabear.pyxlmoose.database

import android.content.Context
import androidx.room.*
import androidx.room.Database
import com.therealbluepandabear.pyxlmoose.dao.PixelArtCreationsDao
import com.therealbluepandabear.pyxlmoose.models.PixelArt

@Database(entities = [PixelArt::class], version = 3)
abstract class PixelArtDatabase: RoomDatabase() {
    abstract fun pixelArtCreationsDao(): PixelArtCreationsDao

    companion object {
        private var instance: PixelArtDatabase? = null
        fun getDatabase(context: Context): PixelArtDatabase {
            if (instance == null) {
                synchronized(PixelArtDatabase::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(context.applicationContext, PixelArtDatabase::class.java, AppData.pixelArtDBFileName)
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return instance!!
        }
    }
}