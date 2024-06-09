package com.example.projectf.presentation.repositorylist.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.projectf.domain.models.Repositories

class DiffUtilCallback: DiffUtil.ItemCallback<Repositories>() {

    override fun areItemsTheSame(oldItem: Repositories, newItem: Repositories): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Repositories, newItem: Repositories): Boolean {
        return oldItem == newItem
    }

}