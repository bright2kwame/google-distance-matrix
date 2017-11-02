package com.monarchy.googledistancemetrix

/**
 * Created by Monarchy on 02/11/2017.
 */
data class DistanceMetrixData(private val distanceData: Double, private val formattedDistanceData: String, private val durationData: Int, private val formattedDurationData: String) {

    override fun toString(): String {
        return "Distance Value: $distanceData Distance Formatted: $formattedDistanceData  Duration Value: $durationData Duration Formatted: $formattedDurationData"
    }
}