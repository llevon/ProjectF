package com.example.projectf.presentation.allusers.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.projectf.domain.models.GithubUser

class DiffUtilCallback: DiffUtil.ItemCallback<GithubUser>() {

    override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem == newItem
    }

}