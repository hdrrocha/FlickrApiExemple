package com.example.flickraiexemple.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flickraiexemple.databinding.RowLoadingErrorBinding

class PhotoItemsLoadStateAdapter(
    private val onRetry: (() -> Unit)? = null
) : LoadStateAdapter<PhotoItemsLoadStateAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowLoadingErrorBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(
        private val binding: RowLoadingErrorBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(state: LoadState) = when (state) {
            is LoadState.NotLoading -> setupDefaultState()
            LoadState.Loading -> setupLoadingState()
            is LoadState.Error -> setupErrorState(state.error.localizedMessage.orEmpty())
        }

        private fun setupLoadingState() = binding.run {
            btRetry.visibility = View.GONE
            tvError.visibility = View.GONE
        }

        private fun setupErrorState(message: String) = binding.run {
            tvError.visibility = View.GONE
            tvError.text = message

            btRetry.visibility = View.VISIBLE
            btRetry.setOnClickListener { onRetry?.invoke() }
        }

        private fun setupDefaultState() = binding.run {
            btRetry.visibility = View.GONE
            tvError.visibility = View.GONE
        }
    }
}
