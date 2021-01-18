package com.aoinc.w4d1_class_kotlinjikan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aoinc.w4d1_class_kotlinjikan.model.JikanResult
import com.aoinc.w4d1_class_kotlinjikan.network.JikanRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class JikanViewModel : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val jikanLiveData: MutableLiveData<List<JikanResult>> = MutableLiveData()
    private val jikanRetrofit: JikanRetrofit = JikanRetrofit()

    fun searchJikan(searchQuery: String) {
        // RxJava implementation
        compositeDisposable.add(
            jikanRetrofit.runSearch(searchQuery)
                .observeOn(AndroidSchedulers.mainThread())  // observe from main thread.
                .subscribeOn(Schedulers.io())   // call on different thread!
                .map { it.jikanResults }    // transforms to the return item we want, can be more specific }
                .subscribe ({
                    if (it.isNotEmpty())
                        jikanLiveData.postValue(it)
                    compositeDisposable.clear()
                }, {
                    Log.d("TAG_X", it.localizedMessage)
                })
        )
    }
}