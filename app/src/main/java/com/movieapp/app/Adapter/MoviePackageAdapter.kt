package com.movieapp.app.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movieapp.app.Models.MovieList
import com.movieapp.app.views.activity.MovieDetailsActivity
import com.movieapp.app.R
import com.movieapp.app.Utils.loadImage
import kotlinx.android.synthetic.main.recyclerview_movie_list.view.*

/**
 * use foe a set data for a movie list
 * */
//TODO MoviePackageAdapter
class MoviePackageAdapter(
    private var context: Context,
    private var moviePackageArrayList: ArrayList<MovieList.Result>
) : RecyclerView.Adapter<MoviePackageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_movie_list, parent, false)
        return ViewHolder(view)
    }

    /**
     * use for a set data binding and set data in view
     * */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getData(moviePackageArrayList[position], position, context)
    }

    override fun getItemCount(): Int {
        return moviePackageArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var movieDetailsTextView = itemView.movieDetailsTextView
        private var movieRateTextView = itemView.movierateTextView
        private var movieDateTextView = itemView.movieDateTextView
        private var movieNameTextView = itemView.movieNameTextView
        private var moviePicImageView = itemView.moviePicImageView
        private var movieListLayout = itemView.movieListLayout

        fun getData(items: MovieList.Result, position: Int, context: Context) {
            movieNameTextView.text = items.originalTitle
            movieDateTextView.text = items.releaseDate
            movieRateTextView.text = items.voteAverage.toString()
            movieDetailsTextView.text = items.overview

            moviePicImageView.loadImage(items.posterPath)
            movieListLayout.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("MovieID", items.id.toString())
                intent.putExtra("releaseDate", items.releaseDate)
                intent.putExtra("originalTitle", items.originalTitle)
                context.startActivity(intent)
            })
        }
    }
}