package com.mmag.ud4_videoretrofitsimple.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mmag.ud4_videoretrofitsimple.databinding.ItemCharacterBinding
import com.mmag.ud4_videoretrofitsimple.network.model.Character

class CharacterAdapter(
    val navigateDetail: (id: Int) -> Unit
) : ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.binding.tvName.text = character.name

        Glide.with(holder.binding.root)
            .load(character.image)
            .into(holder.binding.ivCharacter)

        holder.binding.root.setOnClickListener {
            navigateDetail(character.id)
        }
    }

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)
}

object CharacterItemCallBack : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}