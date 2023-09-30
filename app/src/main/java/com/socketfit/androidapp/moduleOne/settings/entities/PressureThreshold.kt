package com.socketfit.androidapp.moduleOne.settings.entities

data class PressureThreshold(var sensorName : String , var minPressure : Int , var maxPressure : Int){
    override fun toString(): String {
        return "\n Sensor Name : $sensorName ,\n Max Pressure:$maxPressure ,\n MinPressure:$minPressure "
    }
}
