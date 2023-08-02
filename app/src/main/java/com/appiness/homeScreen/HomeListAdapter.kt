package com.appiness.homeScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appiness.databinding.HomeListItemBinding

class HomeListAdapter :RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>(){

    private var list :List<HomeListModelItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        return HomeListViewHolder(HomeListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    fun setDataList(list: List<HomeListModelItem>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class HomeListViewHolder(private val binding: HomeListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:HomeListModelItem){
            binding.listItem = item
        }
    }

}