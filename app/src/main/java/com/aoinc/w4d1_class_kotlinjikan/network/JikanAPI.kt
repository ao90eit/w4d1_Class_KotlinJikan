package com.aoinc.w4d1_class_kotlinjikan.network

import com.aoinc.w4d1_class_kotlinjikan.model.JikanResponse
import com.aoinc.w4d1_class_kotlinjikan.util.Constants
import com.aoinc.w4d1_class_kotlinjikan.util.Constants.Companion.JIKAN_PATH
import com.aoinc.w4d1_class_kotlinjikan.util.Constants.Companion.SEARCH_QUERY
import io.reactivex.Observable
import io.reactivex.ObservableConverter
import retrofit2.http.GET
import retrofit2.http.Query

interface JikanAPI {
    // https://api.jikan.moe/v3/search/anime?q=naruto

    //RxJava implementation
    //Observable<T> (io.reactivex)
    @GET(JIKAN_PATH)
    fun searchJikanAnime(@Query(SEARCH_QUERY) searchQuery: String): Observable<JikanResponse>
    // Java -
    // public Observable<JiknaResponse> searchJikanAnime(@Query(SERACH_QUERY) String searchQuery)
}