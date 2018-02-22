package com.bale.deeplinkdemo.deeplinks

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.bale.deeplinkdemo.BuildConfig

class LinkDispatcherActivity : AppCompatActivity() {

    private val mMapper = UriToIntentMapper(this, IntentHelper())
    private val loginRequest = 1111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data);

        // check if the request code is same as what is passed  here it is 1
        if (requestCode == loginRequest) {
            Toast.makeText(this, "From Login", Toast.LENGTH_LONG).show()
        }

    }
}