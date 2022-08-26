package com.example.mycontactretrofit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactData(
    @PrimaryKey
    val id:Int,
    val name:String,
    val phone:String
)
