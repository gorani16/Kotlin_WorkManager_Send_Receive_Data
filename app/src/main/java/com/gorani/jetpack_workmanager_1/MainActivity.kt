package com.gorani.jetpack_workmanager_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf

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
/**
 * WorkManager 데이터 주고받기 (Sending Data / Receiver Data)
 * => MainActivity <--Data--> WorkManagerA
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        SimpleThread().start()

//        // WorkManagerA 실행
//        val workManagerA = OneTimeWorkRequestBuilder<WorkManagerA>().build()
//        WorkManager.getInstance(this).enqueue(workManagerA)

        // 데이터 보내기 : WorkManager 에서의 Data 는 key/value 형태(pairs) => key to value
        val myData: Data = workDataOf(
            "a" to 10,
            "b" to 20
        )

        // WorkManagerB 실행 => MainActivity 에서 Data 를 WorkManagerB 에 넘길때 setInputData() 를 사용하여 넘긴다.
        val workManagerB = OneTimeWorkRequestBuilder<WorkManagerB>().setInputData(myData).build()
        WorkManager.getInstance(this).enqueue(workManagerB)

        // WorkManagerB 로 부터 반환된 값(output) 을 이곳(MainActivity)에 받기
        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(workManagerB.id)
            .observe(this) { info ->
                if (info != null && info.state.isFinished) {
                    val result = info.outputData.getInt("result", 10000)
                    val result2 = info.outputData.getInt("result2", 22222)
                    Log.d("Activity_Log!!_result", "$result")
                    Log.d("Activity_Log!!_result2", "$result2") // Default 값 실행

                }

            }

    }
}

//class SimpleThread : Thread() {
//    override fun run() {
//        super.run()
//
//        for (i in 1..10) {
//            Log.d("MainActivity_Log!!", "$i")
//            sleep(1000) // sleep() : 지정된 시간만큼 시스템을 멈추게 한다.
//        }
//    }
//}