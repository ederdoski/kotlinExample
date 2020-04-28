package com.adrenastudies.myapplication.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.adrenastudies.myapplication.api.ApiRequest
import com.adrenastudies.myapplication.api.request.UserRequest
import com.adrenastudies.myapplication.interfaces.ApiCallback
import com.adrenastudies.myapplication.model.ListUsers
import com.adrenastudies.myapplication.model.User


class UserViewModel : ViewModel() {

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

    fun getUser(id:String) {
        UserRequest.getUser(id, lvUsers)
    }

    fun getAllUser() {
        UserRequest.getAllUser(lvListUsers)
    }

}