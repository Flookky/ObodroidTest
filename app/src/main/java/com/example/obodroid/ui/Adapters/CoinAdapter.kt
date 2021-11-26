package com.example.obodroid.ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.obodroid.databinding.RecyclerCoinsBinding
import com.example.obodroid.model.Retrofit.response.Coins

class CoinAdapter: RecyclerView.Adapter<CoinAdapter.ViewHolder>() {
    private var list = mutableListOf<Coins>()

    fun setList(coinList: MutableList<Coins>){
        this.list = coinList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerCoinsBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rank.text = list[position].rank.toString()
        holder.coinName.text = list[position].name
        holder.price.text = list[position].price
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(binding: RecyclerCoinsBinding): RecyclerView.ViewHolder(binding.root){
        val rank = binding.rank
        val coinName = binding.name
        val price = binding.price
    }
}