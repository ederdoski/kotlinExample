package com.adrenastudies.myapplication.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.adrenastudies.myapplication.R
import com.adrenastudies.myapplication.adapters.UserAdapter
import com.adrenastudies.myapplication.databinding.FragmentListUserBinding
import com.adrenastudies.myapplication.model.ListUsers
import com.adrenastudies.myapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.header.view.*

class ListUsers: Fragment() {

    private lateinit var ctx:Context;
    private lateinit var listUserObserver: Observer<ListUsers>
    private lateinit var friendsObserver: Observer<Int>
    private lateinit var likesObserver: Observer<Int>
    private lateinit var model: UserViewModel
    private lateinit var binding: FragmentListUserBinding

    private fun initViewModel() {
        model = ViewModelProviders.of(this).get(UserViewModel::class.java)

        listUserObserver = Observer<ListUsers> { listUserObject ->
            setListUsers(binding.rvUserList, listUserObject)
        }

        friendsObserver = Observer<Int> { friends ->
            //binding.header.txtFriends.text = friends.toString()
        }

        likesObserver = Observer<Int> { likes ->
           // binding.header.txtLikes.text = likes.toString()
        }

        model.getListUserObject().observe(this, listUserObserver)
        model.getFriendsObject().observe(this, friendsObserver)
        model.getLikesObject().observe(this, likesObserver)
    }

    private fun setListUsers(mRecyclerView : RecyclerView, listUser: ListUsers?) {
        val mAdapter = UserAdapter()
        mAdapter.UserAdapter(ctx, listUser)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(ctx)
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_user, container, false)
        initViewModel()
        ctx = this.context!!


        /*val navHostFragment = childFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val toolbar = binding.root.findViewById<Toolbar>(R.id.toolbar)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbar.title = destination.label
        }*/

        return binding.root
    }
}