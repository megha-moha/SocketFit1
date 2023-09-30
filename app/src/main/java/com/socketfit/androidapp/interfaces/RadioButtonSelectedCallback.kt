package com.socketfit.androidapp.interfaces

//this interface will be used when the radio button of activity popup is selected
interface RadioButtonSelectedCallback {
    
    fun selectedButton(checkedName: String, checkedId: Boolean)
}
