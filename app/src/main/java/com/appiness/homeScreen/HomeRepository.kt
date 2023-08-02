package com.appiness.homeScreen

import android.util.Log
import com.appiness.TaskCallback
import com.appiness.roomDb.HomeRoomDb
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeRepository @Inject constructor(private val homeRoomDb: HomeRoomDb) {

    private val TAG= HomeRepository::class.simpleName
    private val completedJob = Job()
    private val backgroundScope = CoroutineScope(Dispatchers.IO + completedJob)
    private val foregroundScope = CoroutineScope(Dispatchers.Main)
    lateinit var callback: TaskCallback<List<HomeListModelItem>>
    fun getData(callback: TaskCallback<List<HomeListModelItem>>) {
        this.callback = callback
        addCallBackListener()
    }

    private fun addCallBackListener(){
        backgroundScope.launch {
            try {
                val data = homeRoomDb.roomDao().getAllMessages()
                foregroundScope.launch {
                    callback.onComplete(data)
                }
            } catch (ex: Exception) {
                foregroundScope.launch {
                    callback.onException(ex)
                }
            }
        }
    }

    fun insertData(homeListModelItem: HomeListModelItem) {
        backgroundScope.launch {
            homeRoomDb.roomDao().insert(homeListModelItem)
            addCallBackListener()
        }
    }

    fun getFcmToken(){
        backgroundScope.launch {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.e(TAG, "getFcmToken: tokenError")
                    return@OnCompleteListener
                }
                val token = task.result
                Log.e(TAG, "getFcmToken: $token")
            })
        }
    }
}