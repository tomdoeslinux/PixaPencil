package com.therealbluepandabear.pixapencil.utility

import android.text.InputFilter
import android.text.Spanned

/**
 * [Original StackOverFlow post used to find solution](https://stackoverflow.com/questions/14212518/is-there-a-way-to-define-a-min-and-max-value-for-edittext-in-android?answertab=createdasc#tab-top)
 * I cannot specifically give credit to any user for this solution.
 */

class InputFilterMinMax(private val min: Float, private val max: Float) : InputFilter {
    override fun filter(
        charSequenceSource: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        destStart: Int,
        destEnd: Int
    ): CharSequence? {
        val numberInput: Float = Integer.parseInt(dest.toString() + charSequenceSource.toString()).toFloat()

        return try {
            if (numberInput !in min..max) {
               ""
            } else {
                null
            }
        } catch (exception: Exception) {
            return null
        }
    }

}