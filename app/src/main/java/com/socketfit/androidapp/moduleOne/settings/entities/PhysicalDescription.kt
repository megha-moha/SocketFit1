package com.socketfit.androidapp.moduleOne.settings.entities

data class PhysicalDescription(var factorName : String, var factorMeasurement : String, var factorValue: String){
    override fun toString(): String {
        return "PhysicalDescription(factorName='$factorName', factorMeasurement='$factorMeasurement', factorValue='$factorValue')"
    }
}
