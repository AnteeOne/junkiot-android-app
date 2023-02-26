package tech.antee.junkiot.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.antee.junkiot.data.local.dao.AppDao
import tech.antee.junkiot.data.local.entities.MockEntity

@Database(
    entities = [MockEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao


    companion object {
        const val NAME = "mock-app-database"
    }
}


