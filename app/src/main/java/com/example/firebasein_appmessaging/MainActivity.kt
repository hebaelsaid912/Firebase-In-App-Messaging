package com.example.firebasein_appmessaging

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.inappmessaging.FirebaseInAppMessaging
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks
import com.google.firebase.inappmessaging.model.InAppMessage
import com.google.firebase.installations.FirebaseInstallations

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Installation ID: " + task.result)
            } else {
                Log.e(TAG, "Unable to get Installation ID")
            }
        }
        FirebaseInstallations.getInstance().getToken(/* forceRefresh */ true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Installation auth token: " + task.result?.token)
                } else {
                    Log.e(TAG, "Unable to get Installation auth token")
                }
            }
        val listener = MyClickListener()
        FirebaseInAppMessaging.getInstance().addClickListener(listener)
        FirebaseInAppMessaging.getInstance().setMessagesSuppressed(false)

        FirebaseInAppMessaging.getInstance().addDismissListener {
            Log.d(TAG, "onCreate: campaignId: ${it.campaignMetadata!!.campaignId}")
            Log.d(TAG, "onCreate: isTestMessage: ${it.campaignMetadata!!.isTestMessage}")
            Log.d(TAG, "onCreate: campaignName: ${it.campaignMetadata!!.campaignName}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FirebaseInAppMessaging.getInstance().setMessagesSuppressed(true)
    }
}