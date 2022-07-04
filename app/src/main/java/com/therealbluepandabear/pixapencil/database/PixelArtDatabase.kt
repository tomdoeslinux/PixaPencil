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
)], exportSchema = true)

abstract class PixelArtDatabase: RoomDatabase() {
    abstract fun dao(): PixelArtCreationsDao

    companion object {
        const val LATEST_VERSION = 4

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
}