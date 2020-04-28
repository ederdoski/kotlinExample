package com.adrenastudies.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.adrenastudies.myapplication.databinding.ActivityUserDetailBinding
import com.adrenastudies.myapplication.model.User
import com.adrenastudies.myapplication.model.UserDetail
import com.adrenastudies.myapplication.utils.Functions
import com.adrenastudies.myapplication.viewmodel.UserViewModel

class UserCard : AppCompatActivity() {

    private lateinit var model: UserViewModel
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var userObserver: Observer<User>


    private fun initViewModel() {
        model = ViewModelProviders.of(this).get(UserViewModel::class.java)

        userObserver = Observer<User> { userObject ->

            val user: UserDetail = userObject!!.data
            val imgUser : ImageView = findViewById(R.id.imgUser)

            Functions.setImg(this, imgUser, user.avatar)
            binding.setVariable(BR.user, user)
            binding.executePendingBindings()
        }

        model.getUserObject().observe(this, userObserver)
    }

    fun getUser(id:String) {
        model.getUser(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        initViewModel()
        getUser(Functions.getIntent(this))

    }

}