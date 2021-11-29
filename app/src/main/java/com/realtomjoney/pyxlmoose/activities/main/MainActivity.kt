package com.realtomjoney.pyxlmoose.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputEditText
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding
import com.realtomjoney.pyxlmoose.extensions.navigateHome
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

    override fun onCreationTapped(param: PixelArt) = extendedOnCreationTapped(param)

    fun refreshAdapter() = extendedRefreshAdapter()

    override fun onCreationLongTapped(param: PixelArt) = extendedOnCreationLongTapped(param)

    override fun onBackPressed() {
        binding.floatingActionButton.show()
        this.navigateHome(supportFragmentManager, newCanvasFragmentInstance, binding.mainRoot, binding.newCanvasFragmentHost,"PyxlMoose")
    }

    override fun onDoneButtonPressed(spanCount: Int, textField: TextInputEditText, textFieldTwo: TextInputEditText) {
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
    }
}

