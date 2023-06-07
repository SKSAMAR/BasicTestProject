package sk.samar.testproject.presentation.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
        getAllPosts()
    }

    @SuppressLint("CheckResult")
    fun getPosts() {
        repository.getPosts(
            posts = {
                val postModel = it.map { it.toPostModel() }
                insertPosts(postModel)
                //_screenState.value = ScreenState(data = postModel)
            },
            error = {
                _screenState.value = ScreenState(error = it)
            }
        )
    }

    private fun insertPosts(posts: List<PostModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAllPost(posts)
        }
    }


    private fun getAllPosts(){
        _screenState.value = ScreenState(isLoading = true)
        viewModelScope.launch(Dispatchers.Main){
            repository.getAllPost().collectLatest {
                _screenState.value = ScreenState(data = it)
            }
        }
    }

}