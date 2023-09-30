package com.socketfit.androidapp.moduleOne.sensordata

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.socketfit.androidapp.R
import com.socketfit.androidapp.activity.MainActivity
import com.socketfit.androidapp.databinding.FragmentSensorDataBinding
import com.socketfit.androidapp.interfaces.CustomOnBackPressed


class SensorDataFragment : Fragment() {

    private var _binding: FragmentSensorDataBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sensorDataViewModel =
            ViewModelProvider(this)[SensorDataViewModel::class.java]

        _binding = FragmentSensorDataBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    var popupWindow: PopupWindow? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setOnBackPressedCallback(object : CustomOnBackPressed {
            override fun onCustomBackPressed() {
                popupWindow?.let {
                    if (it.isShowing)
                        it.dismiss()
                }
            }

        })

        binding.ivLimbPoints.setOnClickListener {

            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = inflater.inflate(R.layout.layout_popup_sensor_data, null)

            val layoutParams = WindowManager.LayoutParams()
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT

            popupWindow = PopupWindow(customView, layoutParams.width, layoutParams.height)
            val dimColor = Color.parseColor("#80000000") // Semi-transparent black color
            val backgroundDrawable = ColorDrawable(dimColor)

            popupWindow?.setBackgroundDrawable(backgroundDrawable)
            popupWindow?.isOutsideTouchable = true
            popupWindow?.showAtLocation(binding.frameLayout, Gravity.CENTER, 0, 0)

            customView.findViewById<ImageView>(R.id.iv_popup_close).setOnClickListener {
                popupWindow?.dismiss()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}