package com.bale.deeplinkdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        secureInboxBtn.setOnClickListener {
            startActivity(Intent(this, SecureInboxActivity::class.java))
        }
        profileBtn.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        accountsBtn.setOnClickListener {
            startActivity(Intent(this, AccountsActivity::class.java))
        }
    }
}