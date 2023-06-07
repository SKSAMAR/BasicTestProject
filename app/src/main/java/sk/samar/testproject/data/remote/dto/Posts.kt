package sk.samar.testproject.data.remote.dto

import com.google.gson.annotations.SerializedName
import sk.samar.testproject.domain.model.PostModel
import java.lang.Exception

data class Posts(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("title")
    val title: Any? = null,
    @SerializedName("body")
    val body: Any? = null,
)

fun Posts.toPostModel(): PostModel {
    try {
//        return PostModel(id = id?.toString()?.toInt()?:0, userId = userId?.toString()?.toInt()?:0, title = title?.toString()?:"", body = body?.toString()?:"")
        return PostModel(
            id = id ?: 0,
            userId = userId ?: 0,
            title = title?.toString() ?: "",
            body = body?.toString() ?: ""
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return PostModel(
        id = 0,
        userId = 0,
        title = title?.toString() ?: "",
        body = body?.toString() ?: ""
    )
}
