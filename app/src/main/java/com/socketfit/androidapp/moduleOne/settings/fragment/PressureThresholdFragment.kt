package com.socketfit.androidapp.moduleOne.settings.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.socketfit.androidapp.R
import com.socketfit.androidapp.moduleOne.settings.adapter.AdapterPressureThreshold
import com.socketfit.androidapp.databinding.FragmentPressureThresholdBinding
import com.socketfit.androidapp.moduleOne.settings.entities.PressureThreshold

class PressureThresholdFragment : Fragment() {

    private var _binding: FragmentPressureThresholdBinding? = null
    lateinit var adapter: AdapterPressureThreshold
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPressureThresholdBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterPressureThreshold(getThreshHolds())

        binding.rvPressureThresholds.layoutManager = LinearLayoutManager(activity)
        binding.rvPressureThresholds.adapter = adapter

        binding.buttonSave.setOnClickListener {
            val customDialogView: View =
                LayoutInflater.from(requireActivity()).inflate(R.layout.custom_alert_dialog, null)

            val alertDialog = AlertDialog.Builder(requireActivity())
                .setView(customDialogView)
                .setCancelable(false)
                .create()

            alertDialog.window?.setBackgroundDrawable( ColorDrawable(Color.parseColor("#4FACABAB")));
            alertDialog.show()

            val closeIcon: ImageView = customDialogView.findViewById(R.id.closeIcon)
            closeIcon.setOnClickListener {
                // Dismiss the dialog when the close icon is clicked
                alertDialog.dismiss()
            }
        }


        val backButton = view.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getThreshHolds(): List<PressureThreshold> {

        return listOf(
            PressureThreshold("Sensor 1", 34, 44),
            PressureThreshold("Sensor 2", 29, 56),
            PressureThreshold("Sensor 3", 15, 74),
            PressureThreshold("Sensor 4", 17, 94)
        )

    }
}