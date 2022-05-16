package com.therealbluepandabear.pixapencil.converters

import com.google.gson.Gson

object JsonConverter {
    fun <T> convertListToJsonString(list: List<T>): String {
        return Gson().toJson(list)
    }

    fun convertJsonStringToListOfInt(str: String): List<Int> {
        return Gson().fromJson(str, Array<Int>::class.java).toList()
    }

}