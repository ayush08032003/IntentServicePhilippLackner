package com.ayushwalker.intentservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var btnStartService : Button
    private lateinit var btnStopService : Button
    private lateinit var tvServiceInfo  :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStopService = findViewById(R.id.btnStopService)
        btnStartService = findViewById(R.id.btnStartService)
        tvServiceInfo = findViewById(R.id.tvServiceInfo)

        btnStartService.setOnClickListener {
            Intent(this,MyIntentService::class.java).also{
                startActivity(it)
                tvServiceInfo.text = "Service Running"
            }
        }

        btnStopService.setOnClickListener {
            MyIntentService.stopService()
            tvServiceInfo.text = "Service Stopped"
        }
    }
}

/*
NOTES/STEPS:
Intent Service used to do Background Work, continue to work even when our app is minimized, it works on diff thread,
If multiThreading is used, can't use Intent Services in that
1. For every service we want to add, we need to create a new class, and that class must be inherited from IntentService,
    Each Intent which is passed inside the onHandleIntent() is executed one by one, ad each gets appended to IntentWorkQueue
2. Since you are using an Intent Service, need to add it to our manifest file
    Add <service android:name=".MyIntentService"/> after <activity>

REPORT: Shwoing Error..!
2023-02-15 17:53:13.014  5479-5479  AndroidRuntime          com.ayushwalker.intentservice        E  FATAL EXCEPTION: main
                                                                                                    Process: com.ayushwalker.intentservice, PID: 5479
                                                                                                    android.content.ActivityNotFoundException: Unable to find explicit activity class {com.ayushwalker.intentservice/com.ayushwalker.intentservice.MyIntentService}; have you declared this activity in your AndroidManifest.xml?
                                                                                                    	at android.app.Instrumentation.checkStartActivityResult(Instrumentation.java:2106)
                                                                                                    	at android.app.Instrumentation.execStartActivity(Instrumentation.java:1768)
                                                                                                    	at android.app.Activity.startActivityForResult(Activity.java:5473)
                                                                                                    	at androidx.activity.ComponentActivity.startActivityForResult(ComponentActivity.java:728)
                                                                                                    	at android.app.Activity.startActivityForResult(Activity.java:5426)
                                                                                                    	at androidx.activity.ComponentActivity.startActivityForResult(ComponentActivity.java:709)
                                                                                                    	at android.app.Activity.startActivity(Activity.java:5817)
                                                                                                    	at android.app.Activity.startActivity(Activity.java:5770)
                                                                                                    	at com.ayushwalker.intentservice.MainActivity.onCreate$lambda$1(MainActivity.kt:23)
                                                                                                    	at com.ayushwalker.intentservice.MainActivity.$r8$lambda$aHQGKDbdj3FNB5w_NeFsQBcWzGw(Unknown Source:0)
                                                                                                    	at com.ayushwalker.intentservice.MainActivity$$ExternalSyntheticLambda0.onClick(Unknown Source:2)
                                                                                                    	at android.view.View.performClick(View.java:7498)
                                                                                                    	at com.google.android.material.button.MaterialButton.performClick(MaterialButton.java:1202)
                                                                                                    	at android.view.View.performClickInternal(View.java:7471)
                                                                                                    	at android.view.View.access$3700(View.java:843)
                                                                                                    	at android.view.View$PerformClick.run(View.java:29098)
                                                                                                    	at android.os.Handler.handleCallback(Handler.java:938)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:99)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:233)
                                                                                                    	at android.os.Looper.loop(Looper.java:344)
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8248)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:589)
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1071)
---------------------------- PROCESS ENDED (5479) for package com.ayushwalker.intentservice ----------------------------
 */