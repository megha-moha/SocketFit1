package com.socketfit.androidapp.moduleOne.settings.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.socketfit.androidapp.databinding.FragmentPhysicalDescriptionBinding
import com.socketfit.androidapp.moduleOne.settings.adapter.AdapterPhysicalEducation
import com.socketfit.androidapp.moduleOne.settings.entities.PhysicalDescription

class PhysicalDescriptionFragment : Fragment() {

    private var _binding: FragmentPhysicalDescriptionBinding? = null
    lateinit var adapter : AdapterPhysicalEducation
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPhysicalDescriptionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterPhysicalEducation(getThreshHolds())

        binding.rvPhysicalDescription.layoutManager = LinearLayoutManager(activity)
        binding.rvPhysicalDescription.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getThreshHolds() : List<PhysicalDescription>{

        return listOf(
            PhysicalDescription("Height","KG","78"),
            PhysicalDescription("Weight","CM","179"),
            PhysicalDescription("Amputation Type","","Below Knee"),
            PhysicalDescription("Prosthetic Type","","Transtibial"),
        )

    }
}