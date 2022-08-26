package com.example.mycontactretrofit.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mycontactretrofit.data.models.ContactData

@Dao
interface ContactDao {

    @Insert
    fun insert(vararg contact: ContactData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: List<ContactData>)

    @Delete
    fun delete(contact: List<ContactData>)

    @Update
    fun update(vararg contact: ContactData)

    @Update
    fun update(contact: List<ContactData>)


    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<ContactData>>

    @Query("DELETE FROM contacts")
    fun deleteAll()

    @Transaction
    fun updateAllContact(newList:List<ContactData>){
        deleteAll()
        update(newList)
    }

}