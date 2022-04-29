package com.therealbluepandabear.pixapencil.utility

import android.text.InputFilter
import android.text.Spanned

/**
 * [Original StackOverFlow post used to find solution](https://stackoverflow.com/questions/14212518/is-there-a-way-to-define-a-min-and-max-value-for-edittext-in-android?answertab=createdasc#tab-top)
 * I cannot specifically give credit to any user for this solution.
 */

class InputFilterMinMax(private val min: Int, private val max: Int) : InputFilter {
    override fun filter(
        charSequenceSource: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        destStart: Int,
        destEnd: Int
    ): CharSequence? {
        val numberInput: Int = (dest.toString() + charSequenceSource.toString()).toInt()

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