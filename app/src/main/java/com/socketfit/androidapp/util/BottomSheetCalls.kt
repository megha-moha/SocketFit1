package com.socketfit.androidapp.util

import android.app.Activity
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.socketfit.androidapp.interfaces.CustomBottomSheetCallBacks

class BottomSheetCalls(var custCallBack : CustomBottomSheetCallBacks) : BottomSheetBehavior.BottomSheetCallback() {

    override fun onStateChanged(bottomSheet: View, newState: Int) {
        custCallBack.onStateChanged(bottomSheet, newState)
    }

    override fun onSlide(bottomSheet: View, slideOffset: Float) {
        custCallBack.onSlide()
    }


}