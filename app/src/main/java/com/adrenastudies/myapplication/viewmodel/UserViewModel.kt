package com.adrenastudies.myapplication.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adrenastudies.myapplication.api.request.UserRequest
import com.adrenastudies.myapplication.model.ListUsers
import com.adrenastudies.myapplication.model.User


class UserViewModel : ViewModel() {

    private val likes: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    private val friends: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    private val lvUsers: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    private val lvListUsers: MutableLiveData<ListUsers> by lazy {
        MutableLiveData<ListUsers>()
    }

    fun getUserObject(): LiveData<User> {
        return lvUsers
    }

    fun getListUserObject(): LiveData<ListUsers> {
        return lvListUsers
    }

    fun getFriendsObject(): LiveData<Int> {
        return friends
    }

    fun getLikesObject(): LiveData<Int> {
        return likes
    }

    fun getUser(id:String) {
        UserRequest.getUser(id, lvUsers)
    }

    fun getAllUser() {
        UserRequest.getAllUser(lvListUsers)
    }

    fun getFriends() {
        UserRequest.getFriends(friends)
    }

    fun getLikes() {
        UserRequest.getLikes(likes)
    }

    fun addFriends() {
       UserRequest.addFriends(friends)
    }

    fun removeFriend() {
       UserRequest.removeFriend(friends)
    }

    fun addLikes() {
        UserRequest.addLikes(likes)
    }

}