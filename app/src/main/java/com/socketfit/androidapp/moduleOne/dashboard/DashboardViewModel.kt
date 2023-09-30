package com.socketfit.androidapp.moduleOne.dashboard

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.TimeUnit

class DashboardViewModel : ViewModel() {

    private val mInterval = 1000 // 1 second in this case
    private var mHandler: Handler? = null
    private var timeInSeconds = 0L

    var isTimerRunning: Boolean = false
    private val estimatedTime: MutableLiveData<String> = MutableLiveData()

    //start timer method
    fun startTimer() {

        if (!isTimerRunning) {

            mHandler = Handler(Looper.getMainLooper())
            isTimerRunning = true
            mStatusChecker.run()

            Log.v("TimerData", "Start timer true")


        } else {

            timeInSeconds = 0L
            isTimerRunning = false
            estimatedTime.postValue("stop")

            mHandler?.removeCallbacks(mStatusChecker)
            mHandler?.removeCallbacksAndMessages(null)
            Log.v("TimerData", "Start timer false")

        }
    }

    fun stopTimer() {

        timeInSeconds = 0L
        isTimerRunning = false
        estimatedTime.postValue("stop")

        mHandler?.removeCallbacks(mStatusChecker)
        mHandler?.removeCallbacksAndMessages(null)

    }

    //this method will send data to fragment to show timer on ui
    private var mStatusChecker: Runnable = object : Runnable {
        override fun run() {

            try {

                timeInSeconds += 1
                val formattedTime = getFormattedStopWatch((timeInSeconds * 1000))
                Log.e("formattedTime", formattedTime)
                estimatedTime.postValue(formattedTime)

            } finally {

                mHandler!!.postDelayed(this, mInterval.toLong())

            }
        }
    }

    //get estimated time
    fun getEstimatedTime(): MutableLiveData<String> {
        return estimatedTime
    }

    //format the system time in use readable time
    fun getFormattedStopWatch(ms: Long): String {

        var milliseconds = ms
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        milliseconds -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        return "${if (minutes < 10) "0" else ""}$minutes:" +
                "${if (seconds < 10) "0" else ""}$seconds"
    }

    /*
    private fun updateEstimatedTime() {

        if (isTimerRunning) {

            Log.v("TimerData","in updateEstimatedTime 46")
            val currentTime = System.currentTimeMillis()
            val elapsedTime = currentTime - startTime
            val minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % 60
            val formattedTime = String.format("%02d:%02d", minutes, seconds)
            estimatedTime.value = formattedTime
            // Update estimated time every second
            // Schedule the next update after 1 second

            myRunnable.run {
                estimatedTime.postValue(formattedTime)
            }

            estimatedTimeHandler.postDelayed(::updateEstimatedTime, 1000)

        }else{
            Log.v("TimerData","in updateEstimatedTime 59")
            estimatedTime.postValue("stop")
            estimatedTimeHandler.removeCallbacksAndMessages(null)
        }
    }*/


}