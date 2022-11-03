package com.tbondarenko.testxmlcompose.presentation.screens.listFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tbondarenko.testxmlcompose.databinding.ItemEmojiBinding
import com.tbondarenko.testxmlcompose.presentation.models.EmojiUi
import com.tbondarenko.testxmlcompose.presentation.screens.utils.fromHtml

class EmojiRecyclerViewAdapter(private val onItemClicked: (EmojiUi) -> Unit) :
    ListAdapter<EmojiUi, EmojiRecyclerViewAdapter.EmojiViewHolder>(DiffCallBack) {

    private var emojiList = mutableListOf<EmojiUi>()

    fun add(list: List<EmojiUi>) {
        emojiList = list.toMutableList()
        submitList(emojiList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        val viewHolder = EmojiViewHolder(
            ItemEmojiBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EmojiViewHolder(private val binding: ItemEmojiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(emoji: EmojiUi) {
            with(binding) {
                textviewEmoji.text = emoji.html.fromHtml()
                textviewEmojiName.text = emoji.name
            }
        }
    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<EmojiUi>() {
            override fun areItemsTheSame(oldItem: EmojiUi, newItem: EmojiUi): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: EmojiUi, newItem: EmojiUi): Boolean {
                return oldItem == newItem
            }
        }
    }
}