package com.monarchy.googledistancemetrix

import android.content.Context
import android.util.Log
import com.koushikdutta.ion.Ion
import java.io.UnsupportedEncodingException
import java.net.URLDecoder

/**
 * Created by Monarchy on 02/11/2017.
 */


class MatrixCall(context: Context?, googleMapApiKey: String) {
    private var context: Context? = null
    private var apiKey: String? = ""

    init {
        this.context = context
        this.apiKey = googleMapApiKey
    }

    fun startDistanceMatrix(startLat: String, startLon: String, endLat: String, endLon: String, mode: String, distanceInterface: DistanceMetrixInterface) {
        if (apiKey.isNullOrEmpty()) {
            val message = "Google Map API Key not provided"
            distanceInterface.onReceivedDetail(message)
            return
        }
        var url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=$startLat,$startLon&destinations=$endLat,$endLon&mode=$mode&key=$apiKey"
        try {
            url = URLDecoder.decode(url, "UTF-8")
            Log.e("URL", url)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        Ion.with(this.context)
                .load("GET", url)
                .asJsonObject()
                .setCallback({ e, result ->
                    Log.e("DATA", result.toString() + " " + e)
                    distanceInterface.onRequestEnded()
                    if (result != null && e == null) {
                        val status = SafeRetrieveJsonData().getStringJsonData(result, "status")
                        if (status != "OK") {
                            val errorMessage = SafeRetrieveJsonData().getStringJsonData(result, "error_message")
                            distanceInterface.onReceivedFailed(errorMessage)
                        }else {
                            val rows = result.get("rows").asJsonArray
                            if (rows.size() > 0) {
                                val jsonObject = rows.get(0).asJsonObject
                                val elements = jsonObject.getAsJsonArray("elements")
                                if (elements.size() > 0) {
                                    val elementsJsonObject = elements.get(0).asJsonObject
                                    val jsonObjectDistance = elementsJsonObject.getAsJsonObject("distance")
                                    val jsonObjectDuration = elementsJsonObject.getAsJsonObject("duration")
                                    val distanceValue = SafeRetrieveJsonData().getDoubleJsonData(jsonObjectDistance, "value")
                                    val distanceText = SafeRetrieveJsonData().getStringJsonData(jsonObjectDistance, "text")

                                    val durationValue = SafeRetrieveJsonData().getIntJsonData(jsonObjectDuration, "value")
                                    val durationText = SafeRetrieveJsonData().getStringJsonData(jsonObjectDuration, "text")

                                    val matrixData = DistanceMetrixData(distanceValue, distanceText, durationValue, durationText)
                                    distanceInterface.onReceivedResult(matrixData)

                                } else {
                                    val message = "Unable to find route"
                                    distanceInterface.onReceivedDetail(message)
                                }

                            } else {
                                val message = "Unable to find route"
                                distanceInterface.onReceivedDetail(message)
                            }
                        }

                    } else {
                        val errorMessage = "Unable to find route"
                        distanceInterface.onReceivedFailed(errorMessage)
                    }
                })

    }
}