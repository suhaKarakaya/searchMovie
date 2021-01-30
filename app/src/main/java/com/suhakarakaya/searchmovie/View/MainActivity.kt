package com.suhakarakaya.searchmovie.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.suhakarakaya.searchmovie.Adapter.MovieAdapter
import com.suhakarakaya.searchmovie.R
import com.suhakarakaya.searchmovie.ViewModel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private var movieAdapter = MovieAdapter(arrayListOf(), this)
    var errorMessage: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)








        rv_movieList.layoutManager = LinearLayoutManager(this)
        rv_movieList.adapter = movieAdapter


        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.movies.observe(this, Observer {
            it?.let {
                if (it[0].Response.equals("True")) {
                    rv_movieList.visibility = View.VISIBLE
                    movieAdapter.updateCountryList(it)
                } else {
                    errorMessage = it[0].Error.toString()
                }
            }
        })

        viewModel.errorFlag.observe(this, Observer {
            it?.let {
                if (it) {
                    rv_movieList.visibility = View.GONE
                    txt_no_record.visibility = View.VISIBLE
                    txt_no_record.text = errorMessage
                } else {
                    rv_movieList.visibility = View.VISIBLE
                    txt_no_record.visibility = View.GONE


                }
            }
        })

        viewModel.loading.observe(this, Observer {
            it?.let {
                if (it) {
                    pb_search.visibility = View.VISIBLE
                    txt_no_record.visibility = View.GONE
                    rv_movieList.visibility = View.GONE
                } else {
                    pb_search.visibility = View.GONE
                }
            }
        })
    }

    fun clearText(view: View) {

        edtText_search.setText("")

    }

    fun searchMovie(view: View) {

        viewModel.getDataFromApi(edtText_search.text.toString())

    }
}