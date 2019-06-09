package com.e.example.rcaencrryption

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class FragmentMyProfile : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: com.e.example.rcaencrryption.databinding.FragmentMyProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false)

        VK.execute(VKUsersRequest(), object : VKApiCallback<List<VKMyProfile>> {
            override fun success(result: List<VKMyProfile>) {
                binding.item = result.get(0)
                MYProfile = result.get(0)
                Picasso.get()
                    .load(MYProfile!!.myPhoto200)
                    //.error(errorImage)
                    .into(binding.circleImageView2)
            }

            override fun fail(error: VKApiExecutionException) {
                Log.d("YYYYYYYYYY", " NNNNNNNNNNN")
            }
        })
        Log.d("YYYYYYYYYY", " dddddd")
        return binding.root
    }
}

