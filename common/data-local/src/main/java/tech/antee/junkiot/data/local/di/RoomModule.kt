package tech.antee.junkiot.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tech.antee.junkiot.data.local.dao.AppDao
import tech.antee.junkiot.data.local.db.AppDatabase
import tech.antee.junkiot.di.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.NAME
    ).build()

    @Provides
    @Singleton
    fun appDao(db: AppDatabase): AppDao = db.appDao()
}
