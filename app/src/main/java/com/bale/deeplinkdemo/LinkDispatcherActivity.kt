package com.bale.deeplinkdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bale.deeplinkdemo.deeplinks.IntentHelper
import com.bale.deeplinkdemo.deeplinks.UriToIntentMapper

class LinkDispatcherActivity : AppCompatActivity() {

    private val mMapper = UriToIntentMapper(this, IntentHelper())
    private val loginRequest = 1111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("IS_DEEP_LINK_LOGIN", true)
        startActivityForResult(intent, loginRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data);

        // check if the request code is same as what is passed  here it is 1
        if (requestCode == loginRequest) {
            // Make sure the request was successful

                try {
                    mMapper.dispatchIntent(intent, true)
                } catch (iae: IllegalArgumentException) {
                    if (BuildConfig.DEBUG) {
                        Log.e("Deep links", "Invalid URI", iae)
                    }
                } finally {
                    // Always finish the Activity so that it doesn't stay in our history
                    finish()
                }

        }

    }
}