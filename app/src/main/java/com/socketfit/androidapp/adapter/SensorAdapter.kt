package com.socketfit.androidapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.socketfit.androidapp.R
import com.socketfit.androidapp.database.SensorEntity
import com.socketfit.androidapp.models.Sensor


class SensorAdapter(private val sensorList: List<SensorEntity>) :
    RecyclerView.Adapter<SensorAdapter.SensorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_connected_sensor, parent, false)
        return SensorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val currentSensor = sensorList[position]

        holder.sensorNameTextView.text = currentSensor.name
        holder.sensorCheckBox.isChecked = currentSensor.isSelected
        if (position==sensorList.size-1)
            holder.view1.visibility=View.INVISIBLE
    }

    override fun getItemCount() = sensorList.size

    inner class SensorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sensorNameTextView: TextView = itemView.findViewById(R.id.textView)
        val sensorCheckBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val view1: View = itemView.findViewById(R.id.view1)
    }
}
