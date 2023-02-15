package com.ayushwalker.intentservice

//import android.app.IntentService
import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService : IntentService("MyIntentService") {
    init{
        instance = this
    }

    // companion object, same as static variable in java
    companion object{
        private lateinit var instance:MyIntentService
        var isRunning = false

        // This is created for stopping the Service,
        fun stopService(){
            Log.d("MyIntentService", "Service is Stopping")
            isRunning = false
            instance.stopSelf()
        }

    }
    // this is an Overloaded method
    override fun onHandleIntent(p0: Intent?) {
        try{
            isRunning = true
            while(isRunning){
                Log.d("MyIntentService", "Service is Running....")
                Thread.sleep(1000)
            }
        }catch (e: InterruptedException){
            Thread.currentThread().interrupt()
        }
    }
}