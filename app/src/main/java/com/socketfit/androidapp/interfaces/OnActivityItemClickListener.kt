package com.socketfit.androidapp.interfaces

import android.widget.CompoundButton

//this interface will be used to detect the option clicked even on activity list popup
interface OnActivityItemClickListener {

    fun onActivityItemClick(compoundButton: CompoundButton, flag: Boolean, position: Int)
}