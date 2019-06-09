package com.e.example.rcaencrryption

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.example.rcaencrryption.databinding.FragmentFriendsBinding
import com.squareup.picasso.Picasso
import com.vk.api.sdk.*
import com.vk.api.sdk.exceptions.VKApiExecutionException
import com.vk.api.sdk.exceptions.VKApiIllegalResponseException
import com.vk.api.sdk.internal.ApiCommand
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONException
import org.json.JSONObject


class FragmentFriends : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentFriendsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends, container, false)
        VK.execute(VKFriendsRequest(), object : VKApiCallback<VKFriends> {
            override fun success(result: VKFriends) {
                MYFriends = result
                val llm = LinearLayoutManager(getContext())
                binding.recyclerView.setLayoutManager(llm)
                binding.recyclerView.setAdapter(MYFriends!!.myFriends?.let { RVAdapterFriends(it) })
            }
            override fun fail(error: VKApiExecutionException) {}
        })
        return binding.root
    }
}

