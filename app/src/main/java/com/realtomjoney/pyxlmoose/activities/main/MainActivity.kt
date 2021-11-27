package com.realtomjoney.pyxlmoose.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputEditText
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding
import com.realtomjoney.pyxlmoose.extensions.doSomethingWithChildElements
import com.realtomjoney.pyxlmoose.fragments.NewCanvasFragment
import com.realtomjoney.pyxlmoose.listeners.NewCanvasFragmentListener
import com.realtomjoney.pyxlmoose.listeners.RecentCreationsListener
import com.realtomjoney.pyxlmoose.models.PixelArt

class MainActivity : AppCompatActivity(), RecentCreationsListener, NewCanvasFragmentListener {
    lateinit var binding: ActivityMainBinding
    var hasNavigatedBack = false

    lateinit var newCanvasFragmentInstance: NewCanvasFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        setOnClickListeners()
        setTitle()
    }

    private fun setTitle() {
        title = "Home"
    }

    private fun setOnClickListeners() = extendedSetOnClickListeners()

    override fun onResume() {
        binding.recentCreationsRecyclerView.visibility = View.VISIBLE
        extendedOnResume()
        super.onResume()
    }

    private fun setBindings() = extendedSetBindings()

    private fun navigateHome(fragmentInstance: Fragment, rootLayout: View, newTitle: String) {
        (rootLayout as ViewGroup).doSomethingWithChildElements({ view -> view.visibility = View.VISIBLE })
        binding.newCanvasFragmentHost.visibility = View.GONE
        removeFragmentByInstance(fragmentInstance, newTitle)
    }

    fun navigateTo(fragmentInstance: Fragment, fragmentInstanceId: Int, newTitle: String, hostView: FrameLayout, rootLayout: View) {
        (rootLayout as ViewGroup).doSomethingWithChildElements({ view -> view.visibility = View.GONE })

        newCanvasFragmentInstance = NewCanvasFragment.newInstance()
        hostView.visibility = View.VISIBLE
        (supportFragmentManager.beginTransaction()).replace(fragmentInstanceId, fragmentInstance).commit()

        title = newTitle
    }

    private fun removeFragmentByInstance(fragmentInstance: Fragment, newTitle: String) {
        with(supportFragmentManager.beginTransaction()) {
            remove(fragmentInstance)
            commit()
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        }

        title = newTitle
    }

    override fun onCreationTapped(param: PixelArt) = extendedOnCreationTapped(param)

    fun refreshAdapter() = extendedRefreshAdapter()

    override fun onCreationLongTapped(param: PixelArt) = extendedOnCreationLongTapped(param)

    override fun onBackPressed() {
        navigateHome(newCanvasFragmentInstance, binding.mainRoot, "PyxlMoose")
    }

    override fun onDoneButtonPressed(spanCount: Int, textField: TextInputEditText) {
        var exceptionThrown = false

        try {
            if (spanCount in 1..100) { startActivity(Intent(this, CanvasActivity::class.java).putExtra("SPAN_COUNT", Integer.parseInt(spanCount.toString()))) }
        } catch (ex: Exception) {
            textField.text?.clear()
            exceptionThrown = true
        }

        if (!exceptionThrown) {
            with(supportFragmentManager.beginTransaction()) {
                remove(newCanvasFragmentInstance)
                commit()
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            }

            with(binding) {
                newCanvasFragmentHost.visibility = View.GONE
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}

