package sk.samar.testproject.data.remote

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import sk.samar.testproject.data.remote.dto.Posts

interface ApiServices {

    @GET("posts")
    fun getPosts(): Observable<ArrayList<Posts>>
}