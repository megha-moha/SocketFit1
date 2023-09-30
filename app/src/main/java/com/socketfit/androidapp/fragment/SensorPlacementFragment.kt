package com.socketfit.androidapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.socketfit.androidapp.R
import com.socketfit.androidapp.adapter.AdapterSensorPlacement
import com.socketfit.androidapp.databinding.FragmentSensorPlacementBinding
import com.socketfit.androidapp.interfaces.OnPreferenceAdapterItemClickListener

class SensorPlacementFragment : Fragment() {

    private var _binding: FragmentSensorPlacementBinding? = null
    lateinit var adapter: AdapterSensorPlacement
    private val listOfPreferences =
        listOf<String>("Sensor 1", "Sensor 2", "Sensor 3", "Sensor 4", "Sensor 5", "Sensor 6")

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSensorPlacementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton = view.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        val btnContinue = view.findViewById<Button>(R.id.btnContinue)
        btnContinue.setOnClickListener{
            Toast.makeText(requireContext(),"Sensor Placement data saved successfully",Toast.LENGTH_SHORT).show()
            requireActivity().onBackPressed()
        }
        adapter = AdapterSensorPlacement(listOfPreferences, object :
            OnPreferenceAdapterItemClickListener {
            override fun onItemClick(position: Int) {

            }

            override fun onItemClick(position: Int , flag : Boolean) {
                showCustomLocationView(position , flag)
            }
        })

        binding.rvSensorPlacement.layoutManager = LinearLayoutManager(activity)
        binding.rvSensorPlacement.adapter = adapter
    }

    fun showCustomLocationView(optionIndex : Int , flag : Boolean){

        if(flag){

            binding.clCustomLocation.visibility = View.VISIBLE
            binding.tvStaticText.visibility = View.VISIBLE
            binding.tvSensorLocationName.text = "Sensor ${optionIndex+1} Location"

        }else{

            binding.clCustomLocation.visibility = View.GONE
            binding.tvStaticText.visibility = View.GONE

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}