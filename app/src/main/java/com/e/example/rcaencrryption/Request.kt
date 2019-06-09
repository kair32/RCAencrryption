package com.e.example.rcaencrryption

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONArray
import org.json.JSONObject

class VKUsersRequest: VKRequest<VKMyProfile> {
    constructor(uids: IntArray = intArrayOf()): super("users.get") {
        if (uids.isNotEmpty()) {
            addParam("user_id", uids.joinToString(""))
        }
        addParam("fields", "photo_200, status")
        addParam("name_case", "Nom")
    }

    override fun parse(r: JSONObject): VKMyProfile {
        val users = r.getJSONArray("response")
        val result = VKMyProfile(users.getJSONObject(0).getInt("id"),
                users.getJSONObject(0).getString("photo_200"),
                users.getJSONObject(0).getString("first_name"),
                users.getJSONObject(0).getString("last_name"),
                users.getJSONObject(0).getString("status"))
        return result
    }
}

class VKFriendsRequest: VKRequest<VKFriends> {
    constructor(uids: IntArray = intArrayOf()): super("friends.get") {
        addParam("user_id", uids.joinToString(","))
        MYProfile?.myId?.let { addParam("user_id", it) }
        addParam("order", "hints")
        addParam("fields", "photo_200")
        addParam("count", "25")
    }

    override fun parse(r: JSONObject): VKFriends {
        val users = r.getJSONObject("response")
        val res = ArrayList<FriendItem>()
        val friends = users.getJSONArray("items")
        for (i in 0 until  friends.length())
            res.add(FriendItem(friends.getJSONObject(i).getInt("id"),
                friends.getJSONObject(i).getString("photo_200"),
                friends.getJSONObject(i).getString("first_name"),
                friends.getJSONObject(i).getString("last_name"),
                friends.getJSONObject(i).getInt("online")))
        val result = VKFriends(users.getInt("count"),res)
        return result
    }
}