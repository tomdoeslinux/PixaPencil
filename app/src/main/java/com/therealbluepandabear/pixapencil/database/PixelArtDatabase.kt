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
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.therealbluepandabear.pixapencil.dao.PixelArtCreationsDao
import com.therealbluepandabear.pixapencil.models.PixelArt

@Database(entities = [PixelArt::class], version = PixelArtDatabase.LATEST_VERSION, autoMigrations = [AutoMigration(
    from = 3,
    to = 4,
    spec = PixelArtDatabase.PixelArtDatabaseMigrationThreeToFour::class
), AutoMigration(
    from = 4,
    to = 5,
    spec = PixelArtDatabase.PixelArtDatabaseMigrationFourToFive::class
)], exportSchema = true)

abstract class PixelArtDatabase: RoomDatabase() {
    abstract fun dao(): PixelArtCreationsDao

    companion object {
        const val LATEST_VERSION = 5

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

    @DeleteColumn(tableName = "PixelArt", columnName = "item_rotation")
    class PixelArtDatabaseMigrationThreeToFour : AutoMigrationSpec {
        override fun onPostMigrate(db: SupportSQLiteDatabase) { }
    }

    @DeleteColumn(tableName = "PixelArt", columnName = "item_dimen_cw")
    @DeleteColumn(tableName = "PixelArt", columnName = "item_dimen_ch")
    class PixelArtDatabaseMigrationFourToFive : AutoMigrationSpec {
        override fun onPostMigrate(db: SupportSQLiteDatabase) { }
    }
}