package sk.samar.testproject.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
): Parcelable