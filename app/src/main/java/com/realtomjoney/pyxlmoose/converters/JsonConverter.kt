package com.realtomjoney.pyxlmoose.converters

import com.google.gson.Gson
import com.realtomjoney.pyxlmoose.models.Pixel

object JsonConverter {
    fun convertListOfIntToJsonString(list: List<Int>): String = Gson().toJson(list)
    fun convertJsonStringToListOfInt(str: String) = Gson().fromJson(str, Array<Int>::class.java).toList()
}