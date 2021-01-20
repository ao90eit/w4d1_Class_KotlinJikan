package com.aoinc.w4d1_class_kotlinjikan.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.aoinc.w4d1_class_kotlinjikan.R
import com.aoinc.w4d1_class_kotlinjikan.model.JikanResult
import com.aoinc.w4d1_class_kotlinjikan.util.Constants
import com.aoinc.w4d1_class_kotlinjikan.view.adapter.JikanRecyclerViewAdapter
import com.aoinc.w4d1_class_kotlinjikan.viewmodel.JikanViewModel
import com.aoinc.w4d1_class_kotlinjikan.viewmodel.JikanViewModelFactory
import org.w3c.dom.Text
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class MainActivity : AppCompatActivity() {

    //implementation "androidx.activity:activity-ktx:1.1.0"
    private val viewModel: JikanViewModel by viewModels<JikanViewModel>( factoryProducer = {
        JikanViewModelFactory
    })

    var bgColor = AtomicBoolean(true)
    private lateinit var mainLayout: ConstraintLayout

    //dynamic broadcast receiver creation
    private val broadcastReceiver: BroadcastReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.getStringExtra("nonsense")?.let{

                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()

                if (bgColor.getAndSet(false))
                    mainLayout.setBackgroundColor(Color.GREEN)
                else {
                    mainLayout.setBackgroundColor(Color.RED)
                    bgColor.set(true)
                }
            }
        }

    }

    private lateinit var searchEditText : EditText

    private lateinit var jikanRecyclerView : RecyclerView
    private val jikanAdapter: JikanRecyclerViewAdapter = JikanRecyclerViewAdapter(mutableListOf())

    private var searchTimer: Timer = Timer()

    private val jikanFragment: JikanFragment = JikanFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadJikanFragment()
        Log.d("TAG_X", "${viewModel}")

        mainLayout = findViewById(R.id.main_layout)
        searchEditText = findViewById(R.id.search_text_editText)

        jikanRecyclerView = findViewById(R.id.jikan_recyclerView)
        jikanRecyclerView.adapter = jikanAdapter

        val itemSnapHelper : SnapHelper = LinearSnapHelper()
        itemSnapHelper.attachToRecyclerView(jikanRecyclerView)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // not used
            }

            override fun afterTextChanged(s: Editable?) {
                searchTimer.cancel()
                searchTimer = Timer()

                searchTimer.schedule(
                    object: TimerTask(){
                        override fun run() {
                            Log.d("TAG_X", "$s")
                            viewModel.searchJikan(s.toString())
                        }
                    }, 2000
                )
            }

        })

        viewModel.jikanLiveData.observe(this, Observer {
            Log.d("TAG_X", "Results obtained... ${it.size}")
            jikanAdapter.updateJikanList(it)
        })

//        viewModel.searchJikan("Goku")
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter(Constants.FRAGMENT_BROADCAST_ACTION))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    private fun loadJikanFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.jikan_fragment_frame, jikanFragment)
                .commit()
    }
}