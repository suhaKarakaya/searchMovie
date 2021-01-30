package com.suhakarakaya.searchmovie.Service

import com.suhakarakaya.searchmovie.Model.Movie
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.Query


class SearchMovieApiService {

    private val BASE_URL = "http://www.omdbapi.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getMovies(
        @Query("t") name: String,
        @Query("apikey") apikey: String
    ): Single<Movie> {
        return api.getMovies(name, apikey)
    }


}