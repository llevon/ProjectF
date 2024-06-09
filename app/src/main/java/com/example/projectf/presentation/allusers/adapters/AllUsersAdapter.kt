package com.example.projectf.presentation.allusers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectf.databinding.ItemUserBinding
import com.example.projectf.domain.models.GithubUser


class AllUsersAdapter: ListAdapter <GithubUser, AllUsersAdapter.AllUsersViewHolder>(
    DiffUtilCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUsersViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllUsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllUsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AllUsersViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: GithubUser) = with(binding) {
            tvReponame.text = user.login
            tvRepodesc.text = user.type ?: "No Type"
            Glide.with(binding.root)
                .load(user.avatarUrl)
                .into(binding.ivThumbnail)
        }
    }

}