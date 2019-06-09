package com.e.example.rcaencrryption

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.example.rcaencrryption.databinding.ItemFriendBinding
import java.security.AccessController.getContext

class RVAdapterFriends(val friends: List<FriendItem>) : RecyclerView.Adapter<RVAdapterFriends.FriendsAdapter>() {
    class FriendsAdapter(view: View, binding: ItemFriendBinding) : RecyclerView.ViewHolder(view){
        private var mbinding: ItemFriendBinding = binding
        fun bind(item: FriendItem){
            mbinding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsAdapter {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return FriendsAdapter(binding.root, binding)
    }
    override fun onBindViewHolder(viewHolder: FriendsAdapter, position: Int) {
        viewHolder.bind(friends[position])
    }

    override fun getItemCount(): Int {
        return friends.size
    }
}