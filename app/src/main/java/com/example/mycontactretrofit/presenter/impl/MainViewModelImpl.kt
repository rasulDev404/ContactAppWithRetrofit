package com.example.mycontactretrofit.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycontactretrofit.data.models.ContactData
import com.example.mycontactretrofit.presenter.MainViewModel
import com.example.mycontactretrofit.repository.ContactRepository
import com.example.mycontactretrofit.repository.impl.ContactRepositoryImpl

class MainViewModelImpl : MainViewModel ,ViewModel(){

    private val repository:ContactRepository = ContactRepositoryImpl()
    override val listLiveData: MutableLiveData<List<ContactData>> = MutableLiveData()
    override val progressLiveData: MediatorLiveData<Boolean> = MediatorLiveData()
    override val addLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val editLiveData: MutableLiveData<ContactData> = MutableLiveData()
    override val deleteLiveData: MutableLiveData<ContactData> = MutableLiveData()

    override fun getContacts() {
        progressLiveData.addSource(repository.getContacts()){
            listLiveData.value = it
        }
        fetch()
    }

    override fun edit(contact: ContactData) {
        TODO("Not yet implemented")
    }

    override fun delete(contact: ContactData) {
        TODO("Not yet implemented")
    }

    override fun add() {
        addLiveData.value = Unit
    }

    override fun fetch() {
        repository.fetchContacts()
    }
    init {
        getContacts()
    }
}