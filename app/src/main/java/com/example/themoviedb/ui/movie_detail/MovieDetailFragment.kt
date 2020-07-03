package com.example.themoviedb.ui.movie_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        var messageArgs = arguments?.let {MovieDetailFragmentArgs.fromBundle(it)}
        var movieID = messageArgs?.movieID
        var movieTitle =messageArgs?.movieTitle
        (activity as AppCompatActivity).supportActionBar?.title = movieTitle
        observeDetailViewModel(movieID!!)

    }
    fun observeDetailViewModel(movieID: Int){
        var baseUrl = "https://image.tmdb.org/t/p/w500/"
        movieDetailViewModel.loadMovieDetail(movieID,"670bc282e8edaac05b2f85e6d1895435")
        movieDetailViewModel.getMovieDetail().observe(viewLifecycleOwner, Observer {
            movieDetailLoadError.visibility = View.GONE
            Picasso.get()
                .load(baseUrl+it.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(movieDetailImage)
            movieDetailTitle.text = it.title
            movieDetailOverview.text = it.overview
            movieDetailVoteCount.text = it.vote_count.toString()
            movieReleaseDate.text = it.release_date
        })
            movieDetailViewModel.getLoadError().observe(viewLifecycleOwner, Observer {
            movieDetailLoadError.visibility = View.VISIBLE
            })

    }
}