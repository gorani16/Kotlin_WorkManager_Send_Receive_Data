package com.gorani.jetpack_workmanager_1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class WorkManagerB(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {

        // MainActivity 로 부터 넘어온 Data 받기
        // 만약 넘어온 변수가 비어있을 수도 있으니 Default 값을 설정해놓을 수 있다.
        val a = inputData.getInt("a", 1000)
        val b = inputData.getInt("b", 2000)
        val c = inputData.getInt("c", 3000)

        Log.d("WorkManagerB_Log!!", "$a")
        Log.d("WorkManagerB_Log!!", "$b")
        Log.d("WorkManagerB_Log!!", "$c")   // MainActivity 로 부터 넘어온 값이 없으므로 Log 에서는 Default 값이 찍힌다.


        // WorkManagerB 의 작업이 완료된 후에 MainActivity 로 Data 를 넘기기.
        val output: Data = workDataOf("result" to 1111)

        return Result.success(output)   // WorkManagerB 의 작업이 성공시 넘길 데이터(output) 를 삽입.
    }

}