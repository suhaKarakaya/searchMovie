package com.suhakarakaya.searchmovie.Service

import com.suhakarakaya.searchmovie.Model.Movie
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {


    @GET(".")
    fun getMovies(
        @Query("t") name: String,
        @Query("apikey") apikey: String
    ): Single<Movie>


}