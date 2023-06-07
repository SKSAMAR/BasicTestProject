package sk.samar.testproject.presentation.postsList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import sk.samar.testproject.R
import sk.samar.testproject.databinding.FragmentListBinding
import sk.samar.testproject.domain.adapter.PostListAdapter
import sk.samar.testproject.domain.listener.Receiver
import sk.samar.testproject.domain.model.PostModel
import sk.samar.testproject.presentation.home.MainViewModel
import sk.samar.testproject.util.Constants

class PostsListFragment : Fragment(), Receiver<PostModel> {

    private lateinit var binding: FragmentListBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeState() {
        viewModel.screenState.observe(requireActivity()) {
            val d = viewModel.screenState.value?.data
            binding.postsRecyclerView.adapter =
                PostListAdapter(list = d, context = requireContext(), receiver = this)
            binding.postsRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun receive(data: PostModel) {
        val navController = findNavController()
        val bundle = Bundle()
        bundle.putParcelable(Constants.Post, data)
        navController.navigate(R.id.action_postsList_to_postDetailed, args = bundle)
    }

}