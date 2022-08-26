package com.example.mycontactretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontactretrofit.data.models.ContactData
import com.example.mycontactretrofit.databinding.ItemContactsBinding

class ContactAdapter:ListAdapter<ContactData, ContactAdapter.Holder>(ContactComparator) {

    inner class Holder(val viewBinding: ItemContactsBinding):RecyclerView.ViewHolder(viewBinding.root){
        fun bind(){
            viewBinding.tvName.text = getItem(absoluteAdapterPosition).name
            viewBinding.tvPhone.text = getItem(absoluteAdapterPosition).phone
        }
    }

    object ContactComparator:DiffUtil.ItemCallback<ContactData>(){
        override fun areItemsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.phone == newItem.phone
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
    Holder(ItemContactsBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}