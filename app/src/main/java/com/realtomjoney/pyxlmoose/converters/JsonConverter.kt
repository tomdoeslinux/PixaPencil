package com.realtomjoney.pyxlmoose.converters

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.realtomjoney.pyxlmoose.models.Pixel

object JsonConverter {
    fun convertListOfViewToJsonString(list: List<Pixel>): String {
        return Gson().toJson(list)
    }
    fun convertJsonStringToListOfView(str: String) = Gson().fromJson(str, Array<Pixel>::class.java).toList()
}