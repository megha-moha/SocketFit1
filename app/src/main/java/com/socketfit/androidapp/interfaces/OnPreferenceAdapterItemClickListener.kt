package com.socketfit.androidapp.interfaces

//this interface will be used to get the item click event from adapter of preferences options
interface OnPreferenceAdapterItemClickListener {

    fun onItemClick(position: Int)
    fun onItemClick(position: Int , flag : Boolean)

}