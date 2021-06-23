package com.example.contactbatchinsert

import android.content.ContentProviderOperation
import android.content.Context
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Log

object AddContacts {
    fun batchAddContacts(context: Context, contacts: List<Contact>) {
        if (contacts.isNullOrEmpty()) {
            return
        }
        val ops = ArrayList<ContentProviderOperation>()
        var rawContactInsertIndex = 0
        Log.i("TAG", "batch add contact start")
        Log.i("TAG", "to add ${contacts.size} contacts")
        for (contact in contacts) {
            Log.i("TAG", "to add ${contact.name}, ${contact.telephony}")
            val name = contact.name
            val tel = contact.telephony
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(tel)) {
                continue
            }
            rawContactInsertIndex = ops.size
            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .withYieldAllowed(true)
                .build())
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                .withYieldAllowed(true)
                .build())
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, tel)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .withValue(ContactsContract.CommonDataKinds.Phone.LABEL, "")
                .withYieldAllowed(true)
                .build())
        }
        if (ops.size !=0) {
            Log.i("TAG","ContentProviderOperation size: ${ops.size}")
            context.contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)
        }
        Log.i("TAG", "batch add contact finish")
    }
}