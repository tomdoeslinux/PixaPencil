/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.converters.JSON
import com.therealbluepandabear.pixapencil.dao.ColorPalettesDao
import com.therealbluepandabear.pixapencil.models.ColorPalette
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
                    if (instance == null) instance = Room.databaseBuilder(context.applicationContext, ColorPalettesDatabase::class.java, AppData.colorPalettesDBFileName).fallbackToDestructiveMigration().addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadExecutor().execute {
                                CoroutineScope(Dispatchers.IO).launch {
                                    instance?.colorPalettesDao()?.insertColorPalette(ColorPalette(context.getString(R.string.defaultColorPalette), JSON.listToString(ColorDatabase.toList()), true))
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