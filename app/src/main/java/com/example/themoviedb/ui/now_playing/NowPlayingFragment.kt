package com.example.themoviedb.ui.now_playing

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
import com.example.themoviedb.adapter.NowPlayingAdapter
import com.example.themoviedb.adapter.NowPlayingClickListener
import com.example.themoviedb.model.nowplaying.Result
import kotlinx.android.synthetic.main.fragment_now_playing.*


class NowPlayingFragment : Fragment(), NowPlayingClickListener {
    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private lateinit var nowPlayingAdapter: NowPlayingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        nowPlayingViewModel =  ViewModelProviders.of(this).get(NowPlayingViewModel::class.java)
        var root = inflater.inflate(R.layout.fragment_now_playing, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowPlayingRecyclerView.layoutManager= GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        nowPlayingAdapter = NowPlayingAdapter()
        nowPlayingRecyclerView.adapter = nowPlayingAdapter
        nowPlayingAdapter.setOnClickListener(this)
        observeViewModel()
    }
    fun observeViewModel(){
        var nowPlayingViewModel: NowPlayingViewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        nowPlayingViewModel.loadNowPlaying()
        nowPlayingViewModel.getNowPlaying().observe(viewLifecycleOwner, Observer {
            nowPlayingRecyclerView.visibility = View.VISIBLE
            txtNowPlayingLoadError.visibility = View.GONE
            nowPlayingAdapter.updateNowPlaying(it)
        })
        nowPlayingViewModel.getLoadError().observe(viewLifecycleOwner, Observer {
            nowPlayingRecyclerView.visibility = View.GONE
            txtNowPlayingLoadError.visibility  = View.VISIBLE
        })
    }

    override fun onClick(nowPlayingList: Result) {
        if (!TextUtils.isEmpty(nowPlayingList.id.toString())){
            var nowPlayingID = nowPlayingList.id
            var nowPlayingTitle = nowPlayingList.title
            var action = NowPlayingFragmentDirections.actionNowPlayingFragmentToMovieDetailFragment(nowPlayingID,nowPlayingTitle)
            findNavController().navigate(action)
        }

    }


}