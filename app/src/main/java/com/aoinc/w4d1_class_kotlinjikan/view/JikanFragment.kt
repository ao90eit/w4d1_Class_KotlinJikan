package com.aoinc.w4d1_class_kotlinjikan.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aoinc.w4d1_class_kotlinjikan.R
import com.aoinc.w4d1_class_kotlinjikan.viewmodel.JikanViewModel
import com.aoinc.w4d1_class_kotlinjikan.viewmodel.JikanViewModelFactory

class JikanFragment : Fragment() {

    private lateinit var resultTextView: TextView
    private lateinit var sendBroadcastButton: Button

    private val viewModel: JikanViewModel by viewModels<JikanViewModel>( factoryProducer = {
        JikanViewModelFactory
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.jikan_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultTextView = view.findViewById(R.id.jikan_fragment_textView)
        sendBroadcastButton = view.findViewById(R.id.jikan_fragment_button)

        Log.d("TAG_X", "${viewModel}")

        viewModel.jikanLiveData.observe(viewLifecycleOwner, Observer {
            resultTextView.text = "Result size is ${it.size}"
        })
    }
}