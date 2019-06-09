package com.e.example.rcaencrryption.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.example.rcaencrryption.*
import com.e.example.rcaencrryption.R
import com.e.example.rcaencrryption.databinding.FragmentFriendsBinding
import com.e.example.rcaencrryption.rv_adapters.RVAdapterFriends
import com.vk.api.sdk.*
import com.vk.api.sdk.exceptions.VKApiExecutionException


class FragmentFriends : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentFriendsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_friends, container, false)
        VK.execute(VKFriendsRequest(), object : VKApiCallback<VKFriends> {
            override fun success(result: VKFriends) {
                MYFriends = result
                val llm = LinearLayoutManager(getContext())
                binding.recyclerView.layoutManager = llm
                binding.recyclerView.setAdapter(MYFriends!!.myFriends?.let { RVAdapterFriends(it) })
            }
            override fun fail(error: VKApiExecutionException) {}
        })
        return binding.root
    }
}

