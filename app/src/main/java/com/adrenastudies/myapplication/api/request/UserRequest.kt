package com.adrenastudies.myapplication.api.request

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.adrenastudies.myapplication.api.ApiRequest
import com.adrenastudies.myapplication.interfaces.ApiCallback
import com.adrenastudies.myapplication.model.ListUsers
import com.adrenastudies.myapplication.model.User

class UserRequest {

    companion object {

        var quantityLikes:Int = 0
        var quantityFriends:Int = 0

        fun getUser(id:String, userObject: MutableLiveData<User>) {
            ApiRequest.getUser(id, object : ApiCallback() {
                override fun onSuccess(data: Any) {
                    super.onSuccess(data)
                    userObject.value = data as User
                }

                override fun onError() {
                    super.onError()
                    userObject.value = null
                }
            })
        }

        fun getAllUser(lvListUsers: MutableLiveData<ListUsers>) {
            ApiRequest.getAllUsers(object : ApiCallback() {
                override fun onSuccess(data: Any) {
                    super.onSuccess(data)
                    lvListUsers.value = data as ListUsers
                }

                override fun onError() {
                    super.onError()
                    lvListUsers.value = null
                }
            })
        }

        fun getLikes(likes: MutableLiveData<Int>) {
            likes.value = quantityLikes
        }

        fun getFriends(friends: MutableLiveData<Int>) {
            friends.value = quantityFriends
        }

        fun addFriends(friends: MutableLiveData<Int>) {
            friends.value = quantityFriends + 1
            quantityFriends ++
        }

        fun removeFriend(friends: MutableLiveData<Int>) {
            friends.value = quantityFriends - 1
            quantityFriends --
        }

        fun addLikes(likes: MutableLiveData<Int>) {
            likes.value = quantityLikes + 1
            quantityLikes++
        }

    }

}