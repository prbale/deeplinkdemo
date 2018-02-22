package com.bale.deeplinkdemo.deeplinks

import android.content.Context
import android.content.Intent
import android.net.Uri

class UriToIntentMapper(private val mContext: Context, private val mIntents: IntentHelper) {

    fun dispatchIntent(intent: Intent, isLoginRequired: Boolean = true) {
        val uri = intent.data
        var dispatchIntent: Intent? = null

        if (uri == null) throw IllegalArgumentException("Uri cannot be null")

        val scheme = uri.scheme.toLowerCase()
        val host = uri.host.toLowerCase()

        if ("example-scheme" == scheme) {
            dispatchIntent = mapAppLink(uri)
        }

        if (dispatchIntent != null) {
            if(isLoginRequired) {
                (mContext as LinkDispatcherActivity).startActivityForResult(mIntents.newLoginIntent(mContext, true), 111)
            }
            else
                mContext.startActivity(dispatchIntent)
        }
    }

    private fun mapAppLink(uri: Uri): Intent? = when (uri.host.toLowerCase()) {
        "accounts"      -> mIntents.newAccountsActivityIntent(mContext)
        "secureinbox"   -> mIntents.newSecureInboxActivityIntent(mContext,
                            uri.getQueryParameter("query"))
        "profile"       -> mIntents.newProfileActivityIntent(mContext,
                            uri.getQueryParameter("query"),
                            Integer.parseInt(uri.getQueryParameter("choice")))
        else            -> null
    }

}