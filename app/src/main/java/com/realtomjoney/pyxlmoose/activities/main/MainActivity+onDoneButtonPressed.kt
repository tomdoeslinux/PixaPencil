package com.realtomjoney.pyxlmoose.activities.main

import android.content.Intent
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputEditText
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity

fun MainActivity.extendedOnDoneButtonPressed(spanCount: Int, textField: TextInputEditText, textFieldTwo: TextInputEditText) {
    if (spanCount in 1..100) {
        startActivity(
            Intent(this, CanvasActivity::class.java)
                .putExtra("SPAN_COUNT", Integer.parseInt(spanCount.toString()))
                .putExtra("PROJECT_TITLE", textFieldTwo.text.toString())
        )
    }
    with(supportFragmentManager.beginTransaction()) {
        remove(newCanvasFragmentInstance)
        commit()
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
    }

    with(binding) {
        newCanvasFragmentHost.visibility = View.GONE
        bottomNavigationView.visibility = View.VISIBLE
    }

    title = "PyxlMoose"
}