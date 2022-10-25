package com.example.geideatask.feature.users.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.geideatask.R
import com.example.geideatask.databinding.ItemUserBinding
import com.example.geideatask.feature.users.data.database.User

class UsersListAdapter(private val onUserClicked: (Int) -> Unit) :
    ListAdapter<User, UsersListAdapter.DevicesViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DevicesViewHolder {
        return DevicesViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DevicesViewHolder(
        private val binding: ItemUserBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) = with(binding) {
            tvUserId.text = user.id.toString()
            tvUserName.text = binding.root.context.getString(
                R.string.user_name_place_holder,
                user.first_name,
                user.last_name
            )

            root.setOnClickListener {
                onUserClicked.invoke(user.id)
            }
        }
    }
}

private class DiffUtilCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(
        oldItem: User,
        newItem: User,
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: User,
        newItem: User,
    ) = oldItem == newItem
}
