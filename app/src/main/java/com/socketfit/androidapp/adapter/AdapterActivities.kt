package com.socketfit.androidapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.socketfit.androidapp.databinding.ItemViewActivityBinding
import com.socketfit.androidapp.interfaces.OnActivityItemClickListener

class AdapterActivities(private val dataList: Array<String>, private val onClickListener: OnActivityItemClickListener) :
    RecyclerView.Adapter<AdapterActivities.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemViewActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(private val binding: ItemViewActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.rbActivityItem.setOnCheckedChangeListener { compoundButton, flag ->

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClickListener.onActivityItemClick(compoundButton, flag , -1)
                }

            }

        }

        fun bind(data: String) {
            binding.rbActivityItem.text = data
        }
    }

}