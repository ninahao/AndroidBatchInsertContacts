package com.example.contactbatchinsert

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {
    var addContactProgress: ProgressBar? = null

    var mHandler: Handler? = null
    var mCreateAndInsertContactsBackground: CreateAndInsertContacts? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addContactProgress = findViewById(R.id.addContactProgress)
    }

    fun addContact(view: View) {
        Log.i("TAG", "start")
        doBatch()
    }

    private fun doBatch() {
        val handlerTread = HandlerThread("addContact")
        handlerTread.start()
        mHandler = object : Handler(handlerTread.looper) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.i("TAG", "finish")
            }
        }
        mHandler?.let {
            mCreateAndInsertContactsBackground = CreateAndInsertContacts(this, it)
            mHandler?.post(mCreateAndInsertContactsBackground!!)
        }
    }

    override fun onDestroy() {
        mHandler?.let { handler ->
            mCreateAndInsertContactsBackground?.let { task ->
                handler.removeCallbacks(task)
            }
        }
        super.onDestroy()
    }
}