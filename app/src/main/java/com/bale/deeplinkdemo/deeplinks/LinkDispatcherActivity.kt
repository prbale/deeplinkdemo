package com.bale.deeplinkdemo.deeplinks

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.bale.deeplinkdemo.BuildConfig
import com.bale.deeplinkdemo.DashboardActivity

/**
 * This activity will work as a "Controller" when deep linking comes into picture.
 * Responsibilities:
 *      1. Check whether application being invoked for proper host / scheme
 *      2. Create required journey instance intent with all query parameters to intent.
 *      3. Get the callback when journey terminated. Then launch the dashboard for normal usage.
 */
class LinkDispatcherActivity : AppCompatActivity() {

    private val mMapper = UriToIntentMapper(this)
    private val deepLinkJourneyRequestCode = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            mMapper.dispatchIntent(intent)
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
        if (requestCode == deepLinkJourneyRequestCode) {
            Toast.makeText(this, "Journey Finished", Toast.LENGTH_LONG).show()
            val intent = Intent(this, DashboardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
}