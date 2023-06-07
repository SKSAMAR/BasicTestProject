package sk.samar.testproject.presentation.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sk.samar.testproject.data.remote.dto.Posts
import sk.samar.testproject.data.remote.dto.toPostModel
import sk.samar.testproject.domain.model.PostModel
import sk.samar.testproject.domain.model.ScreenState
import sk.samar.testproject.domain.repositories.Repository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val _screenState = MutableLiveData(ScreenState<List<PostModel>>())
    val screenState = _screenState as LiveData<ScreenState<List<PostModel>>>

    init {
        getPosts()
    }

    @SuppressLint("CheckResult")
    fun getPosts() {
        _screenState.value = ScreenState(isLoading = true)
        repository.getPosts(
            posts = {
                 val postModel = it.map { it.toPostModel() }
                _screenState.value = ScreenState(data = postModel)
            },
            error = {
                _screenState.value = ScreenState(error = it)
            }
        )
    }


}