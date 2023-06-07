package sk.samar.testproject.data.repositories

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.Flow
import sk.samar.testproject.data.remote.ApiServices
import sk.samar.testproject.data.remote.db.dao.PostDao
import sk.samar.testproject.data.remote.dto.Posts
import sk.samar.testproject.domain.model.PostModel
import sk.samar.testproject.domain.repositories.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: ApiServices, private val dao: PostDao): Repository {

    @SuppressLint("CheckResult")
    override fun getPosts(posts: (ArrayList<Posts>)->Unit, error:(String)->Unit){
        api.getPosts()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                posts.invoke(it)
            },{
                it.printStackTrace()
                error.invoke(it.localizedMessage?:"Some Error")
            })

    }

    override suspend fun insertAllPost(posts: List<PostModel>) {
        dao.insertPosts(posts)
    }

    override fun getAllPost(): Flow<List<PostModel>> {
        return dao.getAllPosts()
    }
}