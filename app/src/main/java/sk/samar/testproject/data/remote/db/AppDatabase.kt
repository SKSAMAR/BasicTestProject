package sk.samar.testproject.data.remote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import sk.samar.testproject.data.remote.db.dao.PostDao
import sk.samar.testproject.domain.model.PostModel

@Database(entities = [PostModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
