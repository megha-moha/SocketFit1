package com.socketfit.androidapp.ui.swipebtn

import android.view.MotionEvent
import android.view.View

internal object TouchUtils {
    @JvmStatic
    fun isTouchOutsideInitialPosition(event: MotionEvent, view: View): Boolean {
        return event.x > view.x + view.width
    }
}