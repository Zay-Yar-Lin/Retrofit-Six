package com.example.themoviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.top_rated_item.view.*
import com.example.themoviedb.model.Result

class TopRatedAdapter(var topRatedList:List<Result> = ArrayList()):RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {

    private var clickListener:ClickListener?= null
    fun setOnClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }

inner class TopRatedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{

    lateinit var topRatedList: Result
    fun bindTopRated(topRated:Result){
    this.topRatedList=topRated
        var baseUrl = "https://image.tmdb.org/t/p/w500/"
        Picasso.get()
            .load(baseUrl+topRated.poster_path)
            .placeholder(R.drawable.ic_launcher_background)
            .into(itemView.topRatedImage)
        itemView.topRatedTitle.text = topRated.title
    }
    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        clickListener?.onClick(topRatedList)
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
    var viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.top_rated_item,parent,false)
        return TopRatedViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = topRatedList.size

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
    holder.bindTopRated(topRatedList[position])

    }
    fun updateTopRated(topRatedList:List<Result>){
    this.topRatedList = topRatedList
        notifyDataSetChanged()
    }
}
interface ClickListener{
    fun onClick(topRatedList: Result)
}