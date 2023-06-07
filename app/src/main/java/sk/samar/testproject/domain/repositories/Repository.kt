package sk.samar.testproject.domain.repositories

import sk.samar.testproject.data.remote.dto.Posts


interface Repository {
    fun getPosts(posts: (ArrayList<Posts>) -> Unit, error: (String) -> Unit)
}