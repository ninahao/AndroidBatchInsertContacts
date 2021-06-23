package com.example.contactbatchinsert

import android.content.Context
import android.os.Handler
import android.util.Log

class CreateAndInsertContacts(val context: Context, val mHandler: Handler) : Runnable {
    override fun run() {
        val contactList = ArrayList<Contact>()
        for (i in 0 until 1000) {
            val name = RandomValue.getName()
            val tel = RandomValue.getTel()
            val contact = Contact(name, tel)
            contactList.add(contact)
        }
        Log.i("TAG", "contact list: ${contactList.size}")
        AddContacts.batchAddContacts(context, contactList)
        mHandler.sendEmptyMessage(1)
    }
}