package com.example.flickraiexemple.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.flickraiexemple.R
import com.example.flickraiexemple.databinding.RowPhotoItemBinding
import com.example.flickraiexemple.domain.uimodel.PhotoUi
import kotlinx.android.synthetic.main.row_photo_item.view.*

class PhotoViewAdapter : PagingDataAdapter<PhotoUi, PhotoViewAdapter.ViewHolder>(
    PhotoUiItemComparator
) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<RowPhotoItemBinding>(
            inflater, viewType, parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemViewType(position: Int) = R.layout.row_photo_item

    inner class ViewHolder(
        private val binding: RowPhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PhotoUi) {
            binding.item = item
            binding.invalidateAll()
            Glide.with(itemView)
                .load(item?.url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemView.ivPhoto)


            onItemClickListener?.let { listener ->
                itemView.setOnClickListener { listener.onItemClick(item.id) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: String)
    }

    object PhotoUiItemComparator : DiffUtil.ItemCallback<PhotoUi>() {
        override fun areItemsTheSame(
            oldItem: PhotoUi,
            newItem: PhotoUi
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: PhotoUi,
            newItem: PhotoUi
        ) = oldItem == newItem
    }
}
