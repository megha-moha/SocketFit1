package com.socketfit.androidapp.moduleOne.settings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.socketfit.androidapp.databinding.ItemViewPressureThresholdBinding
import com.socketfit.androidapp.moduleOne.settings.entities.PressureThreshold

class AdapterPressureThreshold (private val dataList: List<PressureThreshold>)  : RecyclerView.Adapter<AdapterPressureThreshold.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewPressureThresholdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(private val binding: ItemViewPressureThresholdBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PressureThreshold) {
            binding.tvTitle.text = data.sensorName
            binding.edtMaxValue.setText(data.maxPressure.toString())
            binding.edtMinValue.setText(data.minPressure.toString())
        }
    }

}