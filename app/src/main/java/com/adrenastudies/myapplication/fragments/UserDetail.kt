package com.adrenastudies.myapplication.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.adrenastudies.myapplication.BR
import com.adrenastudies.myapplication.R
import com.adrenastudies.myapplication.databinding.FragmentUserDetailBinding
import com.adrenastudies.myapplication.model.User
import com.adrenastudies.myapplication.model.UserDetail
import com.adrenastudies.myapplication.utils.Functions
import com.adrenastudies.myapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.header.view.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UserDetail: Fragment() {

    private lateinit var model: UserViewModel
    private lateinit var userObserver: Observer<User>
    private lateinit var friendsObserver: Observer<Int>
    private lateinit var likesObserver: Observer<Int>
    private lateinit var binding: FragmentUserDetailBinding

    private fun initViewModel() {
        model = ViewModelProviders.of(this).get(UserViewModel::class.java)

        userObserver = Observer<User> { userObject ->
            val user: UserDetail = userObject!!.data
            setDataUser(user)
        }

        friendsObserver = Observer<Int> { friends ->
            //binding.header.txtFriends.text = friends.toString()
        }

        likesObserver = Observer<Int> { likes ->
            //binding.header.txtLikes.text = likes.toString()
        }

        binding.imgADD.setOnClickListener {
            disableButton(binding.imgADD, "ADD")
            model.addFriends()
        }

        binding.imgFavorite.setOnClickListener {
            disableButton(binding.imgFavorite,"Favorite")
            model.addLikes()
        }

        binding.imgDelete.setOnClickListener {
            disableButton(binding.imgDelete,"Delete")
            model.removeFriend()
        }

        model.getUserObject().observe(this, userObserver)
        model.getFriendsObject().observe(this, friendsObserver)
        model.getLikesObject().observe(this, likesObserver)
    }

    private fun setDataUser(user: UserDetail) {
        binding.setVariable(BR.user, user)
        binding.executePendingBindings()
        Functions.setImg(context, binding.imgUser, user.avatar)
    }

    private fun disableButton(img: ImageView, text:String) {
        img.setColorFilter(ContextCompat.getColor(this.context!!, R.color.gray_disable), PorterDuff.Mode.MULTIPLY)
        img.isClickable = false
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun getUser(id:String) {
        model.getUser(id)
        model.getLikes()
        model.getFriends()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false)
        initViewModel()
        getUser(arguments!!.getString("id"))
        return binding.root
    }

}