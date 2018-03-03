package com.bale.deeplinkdemo.deeplinks

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.bale.deeplinkdemo.AccountsActivity
import com.bale.deeplinkdemo.ProfileActivity
import com.bale.deeplinkdemo.SecureInboxActivity

class UriToIntentMapper(private val mContext: Context) {

    fun dispatchIntent(intent: Intent) {
        val uri = intent.data
        var dispatchIntent: Intent? = null

        // Sanity Check
        if (uri == null) throw IllegalArgumentException("Uri cannot be null")

        val scheme = uri.scheme.toLowerCase()
        val host = uri.host.toLowerCase()

        if ("mobilebanking" == scheme) {
            dispatchIntent = mapAppLink(uri)
        }

        if (dispatchIntent != null) {
            (mContext as? LinkDispatcherActivity)?.startActivityForResult(dispatchIntent, 1000)
        }
    }

    private fun mapAppLink(uri: Uri): Intent? = when (uri.host.toLowerCase()) {
        "accounts"      -> deepLinkActivityIntent(mContext, uri, AccountsActivity::class.java)
        "secureinbox"   -> deepLinkActivityIntent(mContext, uri, SecureInboxActivity::class.java)
        "profile"       -> deepLinkActivityIntent(mContext, uri, ProfileActivity::class.java)
        else            -> null
    }

    private fun deepLinkActivityIntent(context: Context, uri: Uri, className: Class<*>): Intent {
        val intent = Intent(context, className)
        intent.putExtra("URI", uri.toString())
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return intent
    }

}