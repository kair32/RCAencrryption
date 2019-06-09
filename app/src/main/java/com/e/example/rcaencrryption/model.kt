package com.e.example.rcaencrryption

import com.squareup.picasso.Picasso
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import org.json.JSONArray
import org.json.JSONObject

data class VKMyProfile(
    val myId: Int? = null,
    val myPhoto200: String? = null,
    val myFirstName: String? = null,
    val myLastName: String? = null,
    val myStatus: String? = null
)
data class FriendItem(
    val frId: Int? = null,
    val frPhoto200: String? = null,
    val frFirstName: String? = null,
    val frLastName: String? = null,
    val frOnline: Int? = null
)
data class VKFriends(
    val count: Int = 0,
    val myFriends: List<FriendItem>? = null
)
@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String) {
    Picasso.get()
        .load(url)
        //.error(errorImage)
        .into(this)
    //            loadImage="@{item.myPhoto200}"
}