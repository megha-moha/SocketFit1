package com.socketfit.androidapp.moduleOne.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.socketfit.androidapp.databinding.FragmentShareBinding
import com.socketfit.androidapp.moduleOne.settings.entities.PressureThreshold

class ShareFragment : Fragment() {

    private var _binding: FragmentShareBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentShareBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.btnShare?.setOnClickListener {

            val myIntent =  Intent(Intent.ACTION_SEND)
            myIntent.type = "text/plain"

            val sub  = "SocketFitApp : Sensor Data"

            val body1 : String  =  PressureThreshold("Sensor 1",34, 44).toString()
            val body2 : String  =  PressureThreshold("Sensor 2",29, 56).toString()
            val body3 : String  = PressureThreshold("Sensor 3",15, 74).toString()
            val body4 : String  = PressureThreshold("Sensor 4",17, 94).toString()

            myIntent.putExtra(Intent.EXTRA_SUBJECT,sub)
            myIntent.putExtra(Intent.EXTRA_TEXT,body1+body2+body3+body4)
            startActivity(Intent.createChooser(myIntent, "Share Using"))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}