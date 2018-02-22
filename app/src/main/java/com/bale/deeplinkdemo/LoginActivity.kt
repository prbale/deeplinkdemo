package com.bale.deeplinkdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email_sign_in_button.setOnClickListener { attemptLogin() }
    }

    private fun attemptLogin() {
        if(intent != null && intent.extras != null && intent.extras.getBoolean("IS_DEEP_LINK_LOGIN")) {
            setResult(RESULT_OK, null)
        }
        else {
            startActivity(Intent(LoginActivity@ this, DashboardActivity::class.java))
        }
        finish()
    }
}
