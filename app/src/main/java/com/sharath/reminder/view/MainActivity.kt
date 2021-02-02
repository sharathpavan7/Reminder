package com.sharath.reminder.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.sharath.reminder.R
import com.sharath.reminder.services.TimerWorker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var timerWorkRequest: WorkRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn.setOnClickListener {
            timerWorkRequest = OneTimeWorkRequestBuilder<TimerWorker>().build()
            WorkManager
                    .getInstance(MainActivity@this)
                    .enqueue(timerWorkRequest)
        }

        stopBtn.setOnClickListener {
            timerWorkRequest?.let {
                WorkManager.getInstance(MainActivity@this).cancelWorkById(timerWorkRequest.id)
            }
        }
    }
}