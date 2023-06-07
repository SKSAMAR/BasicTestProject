package sk.samar.testproject.data.remote.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import sk.samar.testproject.domain.model.PostModel

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<PostModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostModel>)
}
