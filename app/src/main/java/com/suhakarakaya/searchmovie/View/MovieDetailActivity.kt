package com.suhakarakaya.searchmovie.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suhakarakaya.searchmovie.Model.Movie
import com.suhakarakaya.searchmovie.R
import com.suhakarakaya.searchmovie.Utils.downloadFromUrl
import com.suhakarakaya.searchmovie.Utils.placeHolderProgressBar
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    var movieData = Movie()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        movieData = intent.getSerializableExtra("movieDetail") as Movie

        txt_movie_title.text = movieData.Title
        txt_movie_year.text = "(" + movieData.Year + ")"
        txt_movie_actors.text = movieData.Actors
        txt_movie_director.text = movieData.Director
        txt_movie_runtime.text = movieData.Runtime
        txt_movie_rating.text = movieData.imdbRating
        img_detail_poster.downloadFromUrl(
            movieData.Poster,
            placeHolderProgressBar(this)
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out)
        return true

    }
}