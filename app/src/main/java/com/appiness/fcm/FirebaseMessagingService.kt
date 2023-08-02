package com.appiness.fcm

import android.util.Log
import com.appiness.homeScreen.HomeListModelItem
import com.appiness.homeScreen.HomeRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseMessagingService:FirebaseMessagingService() {

    val TAG = "FirebaseMessagingService"

    @Inject
    lateinit var homeRepository: HomeRepository

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification != null) {
            homeRepository.insertData(HomeListModelItem(Date().time,message.notification!!.body))
        }

    }
}