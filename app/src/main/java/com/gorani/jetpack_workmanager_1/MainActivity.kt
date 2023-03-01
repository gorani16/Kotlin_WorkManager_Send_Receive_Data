package com.gorani.jetpack_workmanager_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

/**
 * WorkManager
 * - 지속적인 작업에 권장되는 솔루션이다.
 * - 앱이 다시 시작되거나 시스템이 재부팅될 때 작업이 예약된 채로 남아 있으면 그 작업은 유지된다.
 * - 대부분의 백그라운드 처리는 지속적인 작업을 통해 가장 잘 처리되므로 WorkManager 는 백그라운드 처리에 권장하는 기본 API 이다.
 * - 백그라운드 작업시 용이하게 쓰인다.
 * 지속적인 작업
 * - 즉시 : 즉시 시작하고 곧 완료해야 하는 작업이다. 이는 신속하게 처리될 수 있다.
 * - 장기 실행 : 더 오래 (10분 이상이 될 수 있음) 실행될 수 있는 작업이다.
 * - 지연 가능 : 나중에 시작하며 주기적으로 실행될 수 있는 예약된 작업이다.
 *
 * https://developer.android.com/topic/libraries/architecture/workmanager?hl=ko
 * https://developer.android.com/codelabs/android-workmanager?hl=ko#0
 *
 * - Coroutine 이 지원된다.
 * - 작업의 순서를 설정할 수 있다. (스케쥴링?)
 * ㄴ> ex) 실행해야 하는 작업이 A, B, C 가 있다고 가정했을 때 아래와 같이 순서를 정하여 순서대로 작업을 실행시킬 수 있다.
 * A -> 1 or 2
 * B -> 2 or 1
 * C -> 3
 * - 작업에 대한 제약 조건을 걸 수 있다.
 *
 * https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work?hl=ko#work-constraints
 *
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SimpleThread().start()

        // WorkManagerA 실행
        val workManagerA = OneTimeWorkRequestBuilder<WorkManagerA>().build()
        WorkManager.getInstance(this).enqueue(workManagerA)

    }
}

class SimpleThread : Thread() {
    override fun run() {
        super.run()

        for (i in 1..10) {
            Log.d("MainActivity_Log!!", "$i")
            sleep(1000) // sleep() : 지정된 시간만큼 시스템을 멈추게 한다.
        }
    }
}