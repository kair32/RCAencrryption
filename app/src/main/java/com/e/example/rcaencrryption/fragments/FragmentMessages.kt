package com.e.example.rcaencrryption.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.example.rcaencrryption.R
import com.e.example.rcaencrryption.databinding.FragmentMessageBinding

class FragmentMessages: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMessageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false)
        return binding.root
    }
}