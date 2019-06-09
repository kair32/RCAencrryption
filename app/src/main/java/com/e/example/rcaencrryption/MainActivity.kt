package com.e.example.rcaencrryption

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.e.example.rcaencrryption.fragments.FragmentChat
import com.e.example.rcaencrryption.fragments.FragmentFriends
import com.e.example.rcaencrryption.fragments.FragmentMyProfile
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VK.initialize(this)
        VK.addTokenExpiredHandler(tokenTracker)
        VK.login(this,arrayListOf(VKScope.PHOTOS, VKScope.FRIENDS, VKScope.MESSAGES))

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(this)

        navView.menu.getItem(0).isChecked = true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_friends -> {
                val mFragmentManager = supportFragmentManager.beginTransaction()
                mFragmentManager.replace(R.id.fragment, FragmentFriends()).commit()
            }
            R.id.nav_chat -> {
                val mFragmentManager = supportFragmentManager.beginTransaction()
                mFragmentManager.replace(R.id.fragment, FragmentChat()).commit()
            }
            R.id.nav_profil -> {
                val mFragmentManager = supportFragmentManager.beginTransaction()
                mFragmentManager.replace(R.id.fragment, FragmentMyProfile()).commit()
            }
        }
        return true
    }

    private val tokenTracker = object: VKTokenExpiredHandler {
        override fun onTokenExpired() {
            // token expired
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                Log.d("TAGGG", "AUTH")
                // User passed authorization
            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
                Log.d("TAGGG", "NOt")
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
