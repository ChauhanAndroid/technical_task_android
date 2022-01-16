package com.app.slideusers.presentation.ui.feature_user_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.slideusers.databinding.ItemUserBinding
import com.app.slideusers.domain.model.UserModel

class UserListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList : List<UserModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(dataList[position])
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    fun setData(dataList : List<UserModel>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    inner class UserViewHolder( private val itemViewBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(item: UserModel) {
            itemViewBinding.tvName.text = item.name
            itemViewBinding.tvEmail.text = item.email
        }
    }

}