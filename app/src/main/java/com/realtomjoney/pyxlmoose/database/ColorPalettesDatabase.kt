package com.realtomjoney.pyxlmoose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.dao.ColorPalettesDao
import com.realtomjoney.pyxlmoose.models.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(entities = [ColorPalette::class], version = 3)
abstract class ColorPalettesDatabase: RoomDatabase() {
    abstract fun colorPalettesDao(): ColorPalettesDao
    companion object {
        private var instance: ColorPalettesDatabase? = null
        fun getDatabase(context: Context): ColorPalettesDatabase {
            if (instance == null) {
                synchronized(ColorPalettesDatabase::class) {
                    if (instance == null) instance = Room.databaseBuilder(context.applicationContext, ColorPalettesDatabase::class.java, AppData.colorPalettesDBFileName).fallbackToDestructiveMigration().addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadExecutor().execute {
                                CoroutineScope(Dispatchers.IO).launch {
                                    instance?.colorPalettesDao()?.insertColorPalette(ColorPalette("Default color palette", JsonConverter.convertListOfIntToJsonString(ColorDatabase.toList()), true))
                                }
                            }
                        }
                    }).allowMainThreadQueries().build()
                }
            }
            return instance!!
        }
    }
}