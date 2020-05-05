package com.adrenastudies.myapplication.adapters

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import com.adrenastudies.myapplication.R
import com.adrenastudies.myapplication.model.ListUsers
import com.adrenastudies.myapplication.model.UserDetail
import com.adrenastudies.myapplication.utils.Functions


class UserAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var aUsers: ListUsers
    lateinit var context: Context

    class UserVH internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var txtID: TextView = view.findViewById(R.id.txtID)
        var txtName: TextView = view.findViewById(R.id.txtName)
        var txtEmail: TextView = view.findViewById(R.id.txtEmail)
        var imgProfile: ImageView = view.findViewById(R.id.imgUser)
        var txtLastName: TextView = view.findViewById(R.id.txtLastName)
        var lyUserItem: LinearLayout = view.findViewById(R.id.lyUserItem)
    }

    fun UserAdapter(ctx: Context, listUsers: ListUsers?) {
        context = ctx
        aUsers = listUsers!!
    }

    private fun setData(userVH: UserVH, position: Int) {

        val data: UserDetail = aUsers.data[position]

        userVH.txtID.text = data.id
        userVH.txtName.text = data.first_name
        userVH.txtEmail.text = data.email
        userVH.txtLastName.text = data.last_name
        Functions.setImg(context, userVH.imgProfile, data.avatar)

        userVH.lyUserItem.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", data.id)
            Navigation.findNavController(it).navigate(R.id.action_list_user_to_user_detail, bundle)
        }

    }

    private fun setComponents(holder: RecyclerView.ViewHolder, position: Int) {
        val userVH:UserVH = holder as UserVH
        setData(userVH, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.node_item_user, parent, false)
        return UserVH(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        setComponents(holder, position)
    }

    override fun getItemCount(): Int {
        return aUsers.data.size
    }
}