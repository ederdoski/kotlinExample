package com.adrenastudies.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.adrenastudies.myapplication.adapters.UserAdapter
import com.adrenastudies.myapplication.model.ListUsers
import com.adrenastudies.myapplication.model.User
import com.adrenastudies.myapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var model: UserViewModel
    private lateinit var listUserObserver:Observer<ListUsers>

    private fun initViewModel() {
        model = ViewModelProviders.of(this).get(UserViewModel::class.java)

        listUserObserver = Observer<ListUsers> { listUserObject ->
            val mRecyclerView : RecyclerView = findViewById(R.id.rvUserList)
            setListUsers(mRecyclerView, listUserObject)
        }

        model.getListUserObject().observe(this, listUserObserver)
    }

    fun setListUsers(mRecyclerView : RecyclerView, listUser:ListUsers?) {
        val mAdapter = UserAdapter()
        mAdapter.UserAdapter(this, listUser)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
    }

    fun getAllUsers() {
        model.getAllUser()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        getAllUsers()
    }

}
