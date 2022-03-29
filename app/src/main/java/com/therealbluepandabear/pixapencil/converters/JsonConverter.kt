package com.therealbluepandabear.pixapencil.converters

import com.google.gson.Gson

object JsonConverter {
    fun convertListOfIntToJsonString(list: List<Int>): String = Gson().toJson(list)
    fun convertJsonStringToListOfInt(str: String) = Gson().fromJson(str, Array<Int>::class.java).toList()
}