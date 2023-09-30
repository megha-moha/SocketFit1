package com.socketfit.androidapp.interfaces

import android.view.View

//this interface is used to get the callbacks for swipe button actions used on the lock screen
interface CustomBottomSheetCallBacks {
    fun onStateChanged(bottomSheet: View, newState: Int)
    fun onSlide()
}