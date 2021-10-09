package ch.heigvd.iict.sym.labo1

import android.util.Log

object LifeCycleUtils {


    fun start(TAG: String) {

        Log.d(TAG, "onStart() called")
    }

    fun pause(TAG: String) {

        Log.d(TAG, "onPause() called")
    }

    fun resume(TAG: String) {

        Log.d(TAG, "onResume() called")
    }

    fun stop(TAG: String) {

        Log.d(TAG, "onStop() called")
    }

    fun destroy(TAG: String) {

        Log.d(TAG, "onDestroy() called")
    }

}