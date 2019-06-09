package com.e.example.rcaencrryption.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.example.rcaencrryption.MYProfile
import com.e.example.rcaencrryption.R
import com.e.example.rcaencrryption.VKMyProfile
import com.e.example.rcaencrryption.VKUsersRequest
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException

class FragmentMyProfile : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: com.e.example.rcaencrryption.databinding.FragmentMyProfileBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_my_profile, container, false)

        VK.execute(VKUsersRequest(), object : VKApiCallback<VKMyProfile> {
            override fun success(result: VKMyProfile) {
                MYProfile = result
                binding.item = result
            }

            override fun fail(error: VKApiExecutionException) {
                Log.d("YYYYYYYYYY", " ТТТТТТТТТ")
            }
        })
        Log.d("YYYYYYYYYY", " dddddd")
        return binding.root
    }
}

