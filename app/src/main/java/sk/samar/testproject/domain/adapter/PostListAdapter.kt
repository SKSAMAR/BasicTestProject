package sk.samar.testproject.domain.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sk.samar.testproject.databinding.PostContentBinding
import sk.samar.testproject.domain.listener.Receiver
import sk.samar.testproject.domain.model.PostModel

class PostListAdapter(private val list: List<PostModel>?, private val context: Context, private val receiver: Receiver<PostModel>) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostContentBinding.inflate(LayoutInflater.from(context))
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        list?.let {
            holder.binding.post = list[position]
            holder.binding.root.setOnClickListener {
                receiver.receive(list[position])
            }
        }
    }

    inner class PostViewHolder(val binding: PostContentBinding) :
        RecyclerView.ViewHolder(binding.root)

}