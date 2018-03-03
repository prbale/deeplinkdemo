package com.bale.deeplinkdemo

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class SecureInboxActivity : AppCompatActivity() {

    var uri: Uri? = null
    var secureMessageId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secure_inbox)

        if(intent.hasExtra("URI")) {
            uri = Uri.parse(intent.getStringExtra("URI"))
            secureMessageId = uri?.getQueryParameter("secureMessageId").toString()
        }

        Toast.makeText(this, "Secure Message Id : " + secureMessageId, Toast.LENGTH_LONG).show()
    }
}