package com.aoinc.w4d1_class_kotlinjikan.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.renderscript.ScriptIntrinsicConvolve3x3
import android.util.Log
import com.aoinc.w4d1_class_kotlinjikan.view.MainActivity

class BootUpReceiver: BroadcastReceiver() {

    // where you perform your operations :D
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TAG_X", "The phone boot is completed...")

        try {
            val intent2 = Intent(context, MainActivity::class.java)
            intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent2)
        } catch (e: Exception) {
            Log.d("TAG_X", e.localizedMessage)
        }

        Log.d("TAG_X", "should be started")
    }
}