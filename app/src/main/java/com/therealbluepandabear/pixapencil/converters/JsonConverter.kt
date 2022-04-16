package com.therealbluepandabear.pixapencil.converters

import com.google.gson.Gson
import com.therealbluepandabear.pixapencil.models.BitmapAction

object JsonConverter {
    fun <T> convertListToJsonString(list: List<T>): String {
        return Gson().toJson(list)
    }

    fun convertJsonStringToListOfInt(str: String): List<Int> {
        return Gson().fromJson(str, Array<Int>::class.java).toList()
    }

    fun convertJsonStringToListOfBitmapAction(str: String): List<BitmapAction> {
        return Gson().fromJson(str, Array<BitmapAction>::class.java).toList()
    }
}