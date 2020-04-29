package com.adrenastudies.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.adrenastudies.myapplication.adapters.UserAdapter
import com.adrenastudies.myapplication.databinding.ActivityMainBinding
import com.adrenastudies.myapplication.databinding.ActivityUserDetailBinding
import com.adrenastudies.myapplication.model.ListUsers
import com.adrenastudies.myapplication.model.User
import com.adrenastudies.myapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var listUserObserver:Observer<ListUsers>
    private lateinit var friendsObserver:Observer<Int>
    private lateinit var likesObserver:Observer<Int>
    private lateinit var model: UserViewModel
    private lateinit var binding: ActivityMainBinding

    private fun initViewModel() {
        model = ViewModelProviders.of(this).get(UserViewModel::class.java)

        listUserObserver = Observer<ListUsers> { listUserObject ->
            setListUsers(binding.rvUserList, listUserObject)
        }

        friendsObserver = Observer<Int> { friends ->
            binding.header.txtFriends.text = friends.toString()
        }

        likesObserver = Observer<Int> { likes ->
            binding.header.txtLikes.text = likes.toString()
        }

        model.getListUserObject().observe(this, listUserObserver)
        model.getFriendsObject().observe(this, friendsObserver)
        model.getLikesObject().observe(this, likesObserver)
    }

    private fun setListUsers(mRecyclerView : RecyclerView, listUser:ListUsers?) {
        val mAdapter = UserAdapter()
        mAdapter.UserAdapter(this, listUser)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
    }

    private fun getAllUsers() {
        model.getAllUser()
        model.getFriends()
        model.getLikes()
    }

    override fun onResume() {
        super.onResume()
        getAllUsers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModel()
    }

}
