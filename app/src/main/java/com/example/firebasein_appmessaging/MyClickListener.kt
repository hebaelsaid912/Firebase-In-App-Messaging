package com.example.firebasein_appmessaging

import android.util.Log
import com.google.firebase.inappmessaging.FirebaseInAppMessagingClickListener
import com.google.firebase.inappmessaging.model.Action
import com.google.firebase.inappmessaging.model.InAppMessage

private const val TAG = "MyClickListener"
class MyClickListener : FirebaseInAppMessagingClickListener {
    override fun messageClicked(inAppMessage: InAppMessage, action: Action) {
        // Determine which URL the user clicked
        val url: String = action.actionUrl!!
        Log.d(TAG, "messageClicked: url: $url")
        // Get general information about the campaign
        val metadata = inAppMessage.campaignMetadata!!
        val messageType = inAppMessage.messageType!!.name
        Log.d(TAG, "messageClicked: metadata: $metadata")
        Log.d(TAG, "messageClicked: messageType: $messageType")
        // Get data bundle for the inapp message
        val dataBundle: Map<*, *>? = inAppMessage.data
        Log.d(TAG, "messageClicked: dataBundle: $dataBundle")
    }
}