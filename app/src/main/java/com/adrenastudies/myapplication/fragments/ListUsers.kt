package com.adrenastudies.myapplication.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.adrenastudies.myapplication.R
import com.adrenastudies.myapplication.adapters.UserAdapter
import com.adrenastudies.myapplication.databinding.FragmentListUserBinding
import com.adrenastudies.myapplication.model.ListUsers
import com.adrenastudies.myapplication.utils.Functions
import com.adrenastudies.myapplication.viewmodel.UserViewModel
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import kotlinx.android.synthetic.main.fragment_list_user.view.*
import kotlinx.android.synthetic.main.header.view.*


class ListUsers: Fragment() {

    private lateinit var ctx:Context
    private lateinit var listUserObserver: Observer<ListUsers>
    private lateinit var friendsObserver: Observer<Int>
    private lateinit var likesObserver: Observer<Int>
    private lateinit var model: UserViewModel
    private lateinit var binding: FragmentListUserBinding
    private lateinit var skeleton: Skeleton

    private fun initViewModel() {
        model = ViewModelProvider(this).get(UserViewModel::class.java)

        listUserObserver = Observer<ListUsers> { listUserObject ->
            setListUsers(binding.rvUserList, listUserObject)
        }

        friendsObserver = Observer<Int> { friends ->
            binding.header.txtFriends.text = friends.toString()
        }

        likesObserver = Observer<Int> { likes ->
            binding.header.txtLikes.text = likes.toString()
        }

        model.getListUserObject().observe(viewLifecycleOwner, listUserObserver)
        model.getFriendsObject().observe(viewLifecycleOwner, friendsObserver)
        model.getLikesObject().observe(viewLifecycleOwner, likesObserver)
    }

    private fun setListUsers(mRecyclerView : RecyclerView, listUser: ListUsers?) {
        val mAdapter = UserAdapter()
        mAdapter.UserAdapter(this.requireContext(), listUser)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
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

    private fun setSkeleton() {
        skeleton = binding.skeletonLayout
        skeleton = binding.rvUserList.applySkeleton(R.layout.node_item_user_skeleton, 4)
        Functions.showSkeleton(skeleton)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_user, container, false)
        setSkeleton()
        initViewModel()
        return binding.root
    }
}