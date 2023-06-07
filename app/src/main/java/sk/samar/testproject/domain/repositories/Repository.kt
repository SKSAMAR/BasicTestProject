package sk.samar.testproject.domain.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import sk.samar.testproject.data.remote.dto.Posts
import sk.samar.testproject.domain.model.PostModel


interface Repository {
    fun getPosts(posts: (ArrayList<Posts>) -> Unit, error: (String) -> Unit)

    suspend fun insertAllPost(posts: List<PostModel>)

    fun getAllPost(): Flow<List<PostModel>>
}