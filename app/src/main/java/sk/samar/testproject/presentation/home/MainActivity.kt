package sk.samar.testproject.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import sk.samar.testproject.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        maintainState()
    }

    private fun maintainState() {
        viewModel.screenState.observe(this) { state ->
            if (state.isLoading) {
                binding.progressBar.isVisible = true
            } else if (state.error.isNotEmpty()) {
                binding.errorMessage.text = state.error
            } else {
                binding.progressBar.isVisible = false
                binding.errorMessage.text = ""
            }
        }
    }
}