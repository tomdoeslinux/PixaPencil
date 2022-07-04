package com.therealbluepandabear.pixapencil.database

import android.content.Context
import androidx.room.*
import androidx.room.Database
import com.therealbluepandabear.pixapencil.dao.PixelArtCreationsDao
import com.therealbluepandabear.pixapencil.models.PixelArt

@Database(entities = [PixelArt::class], version = 4)
abstract class PixelArtDatabase: RoomDatabase() {
    abstract fun dao(): PixelArtCreationsDao

    companion object {
        private var instance: PixelArtDatabase? = null
        fun get(context: Context): PixelArtDatabase {
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