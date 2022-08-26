package com.example.mycontactretrofit.presenter

import androidx.lifecycle.LiveData
import com.example.mycontactretrofit.data.models.ContactData

interface MainViewModel {

    val listLiveData:LiveData<List<ContactData>>

    val progressLiveData:LiveData<Boolean>

    val addLiveData:LiveData<Unit>

    val editLiveData:LiveData<ContactData>

    val deleteLiveData:LiveData<ContactData>

    fun getContacts()

    fun edit(contact:ContactData)

    fun delete(contact:ContactData)

    fun add()

    fun fetch()
}