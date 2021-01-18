package com.aoinc.w4d1_class_kotlinjikan.network

import com.aoinc.w4d1_class_kotlinjikan.model.JikanResponse
import com.aoinc.w4d1_class_kotlinjikan.util.Constants.Companion.BASE_URL
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class JikanRetrofit {
    private var jikanApi: JikanAPI

    // first block run on class creation (no control over this)
    init {
        jikanApi = createApi(createRetrofit())
    }

    private fun createApi(retrofit: Retrofit) : JikanAPI =
        retrofit.create(JikanAPI::class.java)
    // Java: retrofit.create(JikanAPI.class)

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // because we're using RxJava
            .build()
    }

    public fun runSearch(searchQuery: String): Observable<JikanResponse> =
        jikanApi.searchJikanAnime(searchQuery)
}