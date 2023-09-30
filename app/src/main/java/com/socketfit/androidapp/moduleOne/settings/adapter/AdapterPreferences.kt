package com.socketfit.androidapp.moduleOne.settings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.socketfit.androidapp.R
import com.socketfit.androidapp.interfaces.OnPreferenceAdapterItemClickListener

class AdapterPreferences (private val dataList: List<String> ,private val  onClickListener: OnPreferenceAdapterItemClickListener)  : RecyclerView.Adapter<AdapterPreferences.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_preferences, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                // Handle item click here
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClickListener.onItemClick(position)
                }
            }
        }

        fun bind(data: String) {
            itemView.findViewById<MaterialTextView>(R.id.tv_item_title).text = data
        }
    }
}