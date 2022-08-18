package com.therealbluepandabear.pixapencil.utility

import android.text.InputFilter
import android.text.Spanned

class InputFilterMinMax(private val min: Int, private val max: Int) : InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val input = (dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length)).toInt()

            if (input in min..max) {
                return null
            }
        } catch (numberFormatException: NumberFormatException) {
            numberFormatException.printStackTrace()
        }

        return ""
    }
}