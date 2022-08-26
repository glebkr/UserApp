package com.example.myapplication.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.room.UserEntity
import kotlinx.android.synthetic.main.i_user_item.view.*

class UserListAdapter(private val context: Activity) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private var userList = emptyList<UserEntity>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.i_user_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = userList[position]
        holder.itemView.surnameTv.text = curItem.surname
        holder.itemView.nameTv.text = curItem.name
        holder.itemView.secondNameTv.text = curItem.secondName
        holder.itemView.ageTv.text = curItem.age.toString()
        holder.itemView.heightTv.text = curItem.height.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(users: List<UserEntity>) {
        this.userList = users
        notifyDataSetChanged()
    }
}