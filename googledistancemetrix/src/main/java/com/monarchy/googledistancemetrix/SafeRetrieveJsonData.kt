package com.monarchy.googledistancemetrix

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Monarchy on 02/11/2017.
 */
class SafeRetrieveJsonData {

    fun getStringJSONData(data: JSONObject, key: String): String {
        var dataResult = ""
        if (data.has(key)) {
            if (!data.isNull(key)) {
                try {
                    dataResult = data.getString(key)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }
        return dataResult
    }

    fun getStringFromArrayJSONData(data: JSONObject, key: String): String {
        var dataResult = ""
        if (data.has(key)) {
            if (!data.isNull(key)) {
                try {
                    dataResult = data.getJSONArray("extra_images").toString()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }
        return dataResult
    }

    fun getDataBooleanJSONData(data: JSONObject, key: String): Boolean {
        var dataResult = false
        if (data.has(key)) {
            if (!data.isNull(key)) {
                try {
                    dataResult = data.getBoolean(key)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }

        return dataResult
    }

    fun getDataIntJSONData(data: JSONObject, key: String): Int {
        var dataResult = 0
        if (data.has(key)) {
            if (!data.isNull(key)) {
                try {
                    dataResult = data.getInt(key)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }

        return dataResult
    }

    fun getStringJsonData(data: JsonObject?, key: String): String {
        var dataResult = ""
        if (data == null) {
            return dataResult
        }
        if (data.has(key)) {
            if (!data.get(key).isJsonNull) {
                dataResult = data.get(key).asString
            }
        }

        return dataResult
    }

    fun getObjectJsonData(data: JsonObject?, key: String): JsonObject {
        var dataResult = JsonObject()
        if (data == null) {
            return dataResult
        }
        if (data.has(key)) {
            if (!data.get(key).isJsonNull) {
                dataResult = data.get(key).asJsonObject
            }
        }

        return dataResult
    }

    fun getDoubleJsonData(data: JsonObject?, key: String): Double {
        var dataResult = 5.0
        if (data == null) {
            return dataResult
        }
        if (data.has(key)) {
            if (!data.get(key).isJsonNull) {
                dataResult = data.get(key).asDouble
            }
        }

        return dataResult
    }

    fun getBooleanJsonData(data: JsonObject?, key: String): Boolean {
        var dataResult = false
        if (data == null) {
            return dataResult
        }
        if (data.has(key)) {
            if (!data.get(key).isJsonNull) {
                dataResult = data.get(key).asBoolean
            }
        }

        return dataResult
    }


    fun getIntJsonData(data: JsonObject?, key: String): Int {
        var dataResult = 0
        if (data == null) {
            return dataResult
        }
        if (data.has(key)) {
            if (!data.get(key).isJsonNull) {
                dataResult = data.get(key).asInt
            }
        }

        return dataResult
    }

}