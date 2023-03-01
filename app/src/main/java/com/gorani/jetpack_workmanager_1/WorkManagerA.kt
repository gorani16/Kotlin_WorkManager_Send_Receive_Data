package com.gorani.jetpack_workmanager_1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Thread.sleep

class WorkManagerA(private val context: Context, workParameters: WorkerParameters) :
    Worker(context, workParameters) {

    override fun doWork(): Result {

        for (i in 1..10) {
            sleep(1000)
            Log.d("WorkManagerA_Log!!", "$i")
        }

        return Result.success()
    }

}