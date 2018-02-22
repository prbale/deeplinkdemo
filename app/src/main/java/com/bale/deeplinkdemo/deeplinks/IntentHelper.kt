package com.bale.deeplinkdemo.deeplinks

import android.content.Context
import android.content.Intent
import com.bale.deeplinkdemo.AccountsActivity
import com.bale.deeplinkdemo.ProfileActivity
import com.bale.deeplinkdemo.SecureInboxActivity

class IntentHelper {

    companion object {
        const val EXTRA_SECURE_INBOX_QUERY = "com.bale.deeplinkdemo.deeplinks.intents.Intents.EXTRA_SECURE_INBOX_QUERY"
        const val EXTRA_PROFILE_QUERY = "com.bale.deeplinkdemo.deeplinks.intents.Intents.EXTRA_PROFILE_QUERY"
        const val EXTRA_PROFILE_CHOICE = "com.bale.deeplinkdemo.deeplinks.intents.Intents.EXTRA_PROFILE_CHOICE"
    }

    fun newAccountsActivityIntent(context: Context): Intent {
        val intent: Intent = Intent(context, AccountsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return intent
    }

    fun newSecureInboxActivityIntent(context: Context, query: String): Intent {
        val intent: Intent = Intent(context, SecureInboxActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra(EXTRA_SECURE_INBOX_QUERY, query)
        return intent
    }

    fun newProfileActivityIntent(context: Context, query: String, choice: Int): Intent {
        val intent: Intent = Intent(context, ProfileActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra(EXTRA_PROFILE_QUERY, query)
        intent.putExtra(EXTRA_PROFILE_CHOICE, choice)
        return intent
    }
}