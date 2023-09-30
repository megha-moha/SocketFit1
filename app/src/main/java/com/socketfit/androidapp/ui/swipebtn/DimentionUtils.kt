package com.socketfit.androidapp.ui.swipebtn

import android.content.Context

internal object DimentionUtils {
    @JvmStatic
    fun converPixelsToSp(px: Float, context: Context): Float {
        return px / context.resources.displayMetrics.scaledDensity
    }
}