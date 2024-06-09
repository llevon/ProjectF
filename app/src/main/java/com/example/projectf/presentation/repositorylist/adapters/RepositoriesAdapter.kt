package com.example.projectf.presentation.repositorylist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectf.databinding.ItemRepositoryBinding
import com.example.projectf.domain.models.Repositories


class RepositoriesAdapter: ListAdapter<Repositories, RepositoriesAdapter.RepositoriesViewHolder>(
    DiffUtilCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RepositoriesViewHolder(private val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: Repositories) = with(binding) {
            tvReponame.text = user.name
            tvRepodesc.text = user.description ?: "No Description"
            tvPrivacy.text = if (user.private) {
                "Private"
            } else {
                "Public"
            }
        }
    }

}