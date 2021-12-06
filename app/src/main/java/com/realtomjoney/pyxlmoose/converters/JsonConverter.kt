package com.realtomjoney.pyxlmoose.converters

import com.google.gson.Gson
import com.realtomjoney.pyxlmoose.models.Pixel

object JsonConverter {
    fun convertPixelListToJsonString(list: List<Pixel>): String {
        return Gson().toJson(list)
    }
    fun convertJsonStringToPixelList(str: String) = Gson().fromJson(str, Array<Pixel>::class.java).toList()
}