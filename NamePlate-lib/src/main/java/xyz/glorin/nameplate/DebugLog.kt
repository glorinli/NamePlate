package xyz.glorin.nameplate

import android.util.Log

object DebugLog {
    private const val TAG = "NamePlateLogTag"
    fun d(message: String) {
        Log.d(TAG, message)
    }
}