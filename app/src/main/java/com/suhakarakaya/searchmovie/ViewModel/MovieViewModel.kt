package com.suhakarakaya.searchmovie.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suhakarakaya.searchmovie.Model.Movie
import com.suhakarakaya.searchmovie.Service.SearchMovieApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieViewModel() : ViewModel() {

    private val movieApiService = SearchMovieApiService()
    private val disposable = CompositeDisposable()


    val movies = MutableLiveData<List<Movie>>()
    var movieList = ArrayList<Movie>()
    var errorFlag = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()

    fun getDataFromApi(name: String) {
        loading.value = true
        movieList = ArrayList()

        runBlocking {
            launch(Dispatchers.IO) {
                disposable.add(
                    movieApiService.getMovies(name, "fcea38c8")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<Movie>() {
                            override fun onSuccess(t: Movie) {
                                loading.value = false
                                movieList.add(t)
                                movies.value = movieList
                                if (t.Response.equals("True")) {
                                    errorFlag.value = false
                                } else {
                                    errorFlag.value = true
                                }


                            }

                            override fun onError(e: Throwable) {
                                loading.value = false
                                e.printStackTrace()
                            }

                        })
                )
            }
        }


    }
}