package com.monarchy.googledistancemetrix

/**
 * Created by Monarchy on 02/11/2017.
 */
interface DistanceMetrixInterface {

    fun onReceivedResult(result: DistanceMetrixData)

    fun onReceivedDetail(detailMessage: String)

    fun onReceivedFailed(failedMessage: String)

    fun onRequestEnded()
}