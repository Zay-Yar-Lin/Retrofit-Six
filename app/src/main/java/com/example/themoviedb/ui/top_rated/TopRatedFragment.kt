package com.example.themoviedb.ui.top_rated

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.adapter.ClickListener
import com.example.themoviedb.adapter.TopRatedAdapter
import com.example.themoviedb.model.Result
import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : Fragment(), ClickListener {

    private lateinit var topRatedViewModel: TopRatedViewModel
    private lateinit var topRatedAdapter: TopRatedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        topRatedViewModel =ViewModelProviders.of(this).get(TopRatedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_top_rated, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRatedRecyclerView.layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        topRatedAdapter = TopRatedAdapter()
        topRatedRecyclerView.adapter = topRatedAdapter
        topRatedAdapter.setOnClickListener(this)
        observeViewModel()

    }
    fun observeViewModel(){
        var topRatedViewModel: TopRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        topRatedViewModel.loadTopRated()
        topRatedViewModel.getTopRated().observe(viewLifecycleOwner, Observer {
            topRatedRecyclerView.visibility = View.VISIBLE
            txtLoadError.visibility = View.GONE
            topRatedAdapter.updateTopRated(it)
        })
        topRatedViewModel.getLoadError().observe(viewLifecycleOwner, Observer {
        topRatedRecyclerView.visibility = View.GONE
            txtLoadError.visibility = View.VISIBLE
        })
    }


    override fun onClick(topRatedList: Result) {
        if (!TextUtils.isEmpty(topRatedList.id.toString())){
            var movieID = topRatedList.id
            var movieTitle = topRatedList.title
            var action = TopRatedFragmentDirections.actionTopRatedFragmentToMovieDetailFragment(movieID,movieTitle)
            findNavController().navigate(action)
        }
    }
}