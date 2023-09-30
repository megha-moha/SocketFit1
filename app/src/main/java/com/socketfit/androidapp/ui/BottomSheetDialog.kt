package com.socketfit.androidapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.radiobutton.MaterialRadioButton
import com.socketfit.androidapp.R
import com.socketfit.androidapp.interfaces.RadioButtonSelectedCallback
import com.socketfit.androidapp.util.SocketFitApplication


class BottomSheetDialog(val callback: RadioButtonSelectedCallback) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_bottom_sheet, container, false)

        var lastSelectedButton: MaterialRadioButton? = null

        val rb1 = view.findViewById<MaterialRadioButton>(R.id.radioBtnResting)
        val rb2 = view.findViewById<MaterialRadioButton>(R.id.radioBtnWalking)
        val rb3 = view.findViewById<MaterialRadioButton>(R.id.radioBtnJogging)
        val rb4 = view.findViewById<MaterialRadioButton>(R.id.radioBtnRunning)

        val ll_1 = view.findViewById<LinearLayoutCompat>(R.id.ll_resting)
        val ll_2 = view.findViewById<LinearLayoutCompat>(R.id.ll_walking)
        val ll_3 = view.findViewById<LinearLayoutCompat>(R.id.ll_jogging)
        val ll_4 = view.findViewById<LinearLayoutCompat>(R.id.ll_running)

        if (SocketFitApplication.getInstance().currentSelectedActivity == "Resting") {
            rb1.isChecked = true
        } else if (SocketFitApplication.getInstance().currentSelectedActivity == "Walking") {
            rb2.isChecked = true
        } else if (SocketFitApplication.getInstance().currentSelectedActivity == "Jogging") {
            rb3.isChecked = true
        } else if (SocketFitApplication.getInstance().currentSelectedActivity == "Running") {
            rb4.isChecked = true
        }

        rb1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            lastSelectedButton?.isChecked = false

            if (isChecked) {
                ll_1.setBackgroundResource(R.drawable.radio_button_style_selected)
            } else {
                ll_1.setBackgroundResource(R.drawable.radio_button_style_not_selected)
            }

            lastSelectedButton = rb1
            callback.selectedButton("Resting", isChecked)
            SocketFitApplication.getInstance().currentSelectedActivity = "Resting"
        })

        rb2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            lastSelectedButton?.isChecked = false

            if (isChecked) {
                ll_2.setBackgroundResource(R.drawable.radio_button_style_selected)
            } else {
                ll_2.setBackgroundResource(R.drawable.radio_button_style_not_selected)
            }

            lastSelectedButton = rb2
            callback.selectedButton("Walking", isChecked)
            SocketFitApplication.getInstance().currentSelectedActivity = "Walking"

        })

        rb3.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            lastSelectedButton?.isChecked = false

            if (isChecked) {
                ll_3.setBackgroundResource(R.drawable.radio_button_style_selected)
            } else {
                ll_3.setBackgroundResource(R.drawable.radio_button_style_not_selected)
            }

            lastSelectedButton = rb3
            callback.selectedButton("Jogging", isChecked)
            SocketFitApplication.getInstance().currentSelectedActivity = "Jogging"

        })

        rb4.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            lastSelectedButton?.isChecked = false

            if (isChecked) {
                ll_4.setBackgroundResource(R.drawable.radio_button_style_selected)
            } else {
                ll_4.setBackgroundResource(R.drawable.radio_button_style_not_selected)
            }

            lastSelectedButton = rb4
            callback.selectedButton("Running", isChecked)
            SocketFitApplication.getInstance().currentSelectedActivity = "Running"

        })


        return view

    }

}