package com.aoinc.w4d1_class_kotlinjikan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.aoinc.w4d1_class_kotlinjikan.R
import com.aoinc.w4d1_class_kotlinjikan.viewmodel.JikanViewModel

class MainActivity : AppCompatActivity() {

    //implementation "androidx.activity:activity-ktx:1.1.0"
    private val viewModel: JikanViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.jikanLiveData.observe(this, Observer {
            Log.d("TAG_X", "Results obtained... ${it.size}")
        })

        viewModel.searchJikan("Goku")
    }
}