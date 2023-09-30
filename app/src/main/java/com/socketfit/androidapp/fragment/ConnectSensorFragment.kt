package com.socketfit.androidapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.socketfit.androidapp.R
import com.socketfit.androidapp.adapter.AdapterSensorPlacement
import com.socketfit.androidapp.adapter.SensorAdapter
import com.socketfit.androidapp.database.SensorEntity
import com.socketfit.androidapp.databinding.FragmentConnectedSensorsBinding

class ConnectSensorFragment : Fragment() {

    private var _binding: FragmentConnectedSensorsBinding? = null
    lateinit var adapter: AdapterSensorPlacement
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentConnectedSensorsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_sensor_placement)
        val btnContinue = view.findViewById<Button>(R.id.btnContinue)
        btnContinue.setOnClickListener{
            Toast.makeText(requireContext(),"Sendor data saved successfully",Toast.LENGTH_SHORT).show()
            requireActivity().onBackPressed()
        }
        val backButton = view.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        val listOfPreferences = listOf(
            SensorEntity(name = "Sensor 1", isSelected = false, userEmail = ""),
            SensorEntity(name = "Sensor 2", isSelected = false, userEmail = ""),
            SensorEntity(name = "Sensor 3", isSelected = false, userEmail = ""),
            SensorEntity(name = "Sensor 4", isSelected = false, userEmail = ""),
            SensorEntity(name = "Sensor 5", isSelected = false, userEmail = ""),
            SensorEntity(name = "Sensor 6", isSelected = false, userEmail = "")
        )
        recyclerView.adapter = SensorAdapter(listOfPreferences)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}