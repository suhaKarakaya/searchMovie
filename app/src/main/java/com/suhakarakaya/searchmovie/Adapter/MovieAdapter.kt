package com.suhakarakaya.searchmovie.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suhakarakaya.searchmovie.Model.Movie
import com.suhakarakaya.searchmovie.R
import com.suhakarakaya.searchmovie.Utils.downloadFromUrl
import com.suhakarakaya.searchmovie.Utils.placeHolderProgressBar
import com.suhakarakaya.searchmovie.View.MovieDetailActivity
import kotlinx.android.synthetic.main.item_movie.view.*
import java.io.Serializable

class MovieAdapter(var movieList: ArrayList<Movie>, var activity: Activity) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {


    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.view.txt_movie_name.text = movieList[position].Title
        holder.view.txt_movie_genre.text = movieList[position].Genre
        holder.view.txt_movie_plot.text = movieList[position].Plot
        holder.view.img_movie.downloadFromUrl(
            movieList[position].Poster,
            placeHolderProgressBar(holder.view.context)
        )

        holder.view.setOnClickListener {
            var intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra("movieDetail", movieList.get(position) as Serializable)
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out)
        }


    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateCountryList(newCountryList: List<Movie>) {
        movieList.clear()
        movieList.addAll(newCountryList)
        notifyDataSetChanged()
    }


}