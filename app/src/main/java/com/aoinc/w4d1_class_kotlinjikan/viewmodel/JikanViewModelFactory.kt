package com.aoinc.w4d1_class_kotlinjikan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object JikanViewModelFactory : ViewModelProvider.Factory {

    private val viewModel: JikanViewModel = JikanViewModel()
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}