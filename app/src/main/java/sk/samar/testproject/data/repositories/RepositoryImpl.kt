package sk.samar.testproject.data.repositories

import android.annotation.SuppressLint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import sk.samar.testproject.data.remote.ApiServices
import sk.samar.testproject.data.remote.dto.Posts
import sk.samar.testproject.domain.repositories.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: ApiServices): Repository {

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
}