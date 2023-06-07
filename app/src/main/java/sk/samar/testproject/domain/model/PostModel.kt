package sk.samar.testproject.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "posts")
data class PostModel(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
): Parcelable