package com.socketfit.androidapp.moduleOne.settings

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.socketfit.androidapp.R
import com.socketfit.androidapp.activity.LoginActivity
import com.socketfit.androidapp.activity.WelcomeScreenActivity
import com.socketfit.androidapp.databinding.FragmentSettingsBinding
import com.socketfit.androidapp.interfaces.OnPreferenceAdapterItemClickListener
import com.socketfit.androidapp.moduleOne.settings.adapter.AdapterPreferences
import com.socketfit.androidapp.util.SavePreferences


/* settings page */

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    //this adapter is used to show the options in preference screen
    lateinit var adapter: AdapterPreferences

    //list of options to be shown on the screen
    private val listOfPreferences =
        listOf<String>("Sensor Placement", "Pressure Threshold", "Physical Description", "Connected Sensors")

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle("Confirmation")
            alertDialogBuilder.setMessage("Are you sure you want to log out?")
            alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->
                SavePreferences(requireContext()).setEmail("")
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }

            alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

        //initiate the adapter of options to be shown on the screen
        adapter =
            AdapterPreferences(listOfPreferences, object : OnPreferenceAdapterItemClickListener {
                override fun onItemClick(position: Int) {

                    //set on click event target to open. fragments will open on the click
                    if (position == 0) {
                        findNavController().navigate(R.id.action_navigation_settings_to_sensorPlacementFragment)
                    } else if (position == 1) {
                        findNavController().navigate(R.id.action_navigation_settings_to_pressureThresholdFragment)
                    } else if (position == 2) {
                        findNavController().navigate(R.id.action_navigation_settings_to_physicalDescriptionFragment)
                    }else if (position == 3) {
                        findNavController().navigate(R.id.action_navigation_settings_to_connectSensorFragment)
                    }

                }

                override fun onItemClick(position: Int, flag: Boolean) {
                    TODO("Not yet implemented")
                }
            })

        binding.rvPreferences.layoutManager = LinearLayoutManager(activity)
        binding.rvPreferences.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}