package com.socketfit.androidapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.socketfit.androidapp.R
import com.socketfit.androidapp.interfaces.OnPreferenceAdapterItemClickListener
import com.socketfit.androidapp.util.SocketFitApplication

class AdapterSensorPlacement(
    private val dataList: List<String>,
    private val onClickListener: OnPreferenceAdapterItemClickListener
) : RecyclerView.Adapter<AdapterSensorPlacement.ViewHolder>() {

    lateinit var dropdownList: List<String>

    init {

        initSpinnerData()
    }

    lateinit var dropdownListAdapter: ArrayAdapter<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_sensor_placement, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data, position)
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

        fun bind(data: String, positionIndex: Int) {

            val spinner: Spinner = itemView.findViewById(R.id.spinnerSensorPlacement)

            val items = mutableListOf("Sensor ${positionIndex + 1}") // Set the default title
            items.addAll(dropdownList) // Add the dropdown items

            dropdownListAdapter = ArrayAdapter(
                SocketFitApplication.getInstance(),
                android.R.layout.simple_spinner_item,
                items
            )
            dropdownListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = dropdownListAdapter

//            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//
//                    val selectedItem = items[position]
//
//                    if (selectedItem != "Custom Location") {
//
//                        spinner.setSelection(0, false)
//                        onClickListener.onItemClick(positionIndex, false)
//
//                    } else {
//                        onClickListener.onItemClick(positionIndex, true)
//                    }
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    // Handle nothing selected, if needed
//                }
//            }
        }
    }

    private fun initSpinnerData() {

        dropdownList = listOf(
            "End of stump (Distal end of residual limb)",
            "Shin bone (Tibial crest)",
            "Outside of knee (Fibular Head)",
            "Front of knee (Patella Tendon)",
            "Custom Location"
        )


    }
}