package com.socketfit.androidapp.moduleOne.settings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.socketfit.androidapp.databinding.ItemViewPhysicalDescriptionBinding
import com.socketfit.androidapp.moduleOne.settings.entities.PhysicalDescription

class AdapterPhysicalEducation (private val dataList: List<PhysicalDescription>)  : RecyclerView.Adapter<AdapterPhysicalEducation.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewPhysicalDescriptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(private val binding: ItemViewPhysicalDescriptionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PhysicalDescription) {
            binding.tvTile.text = data.factorName
            binding.edtTitle.text = data.factorMeasurement
            binding.edtInput.setText(data.factorValue)
        }
    }

}