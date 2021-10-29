package com.realtomjoney.pyxlmoose

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
@SuppressLint("ClickableViewAccessibility")

class CanvasRecyclerAdapter(private val pixels: List<View>,
                            private val caller: CanvasFragmentListener,
                            private val isGridVisible: Boolean) :
    RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context), parent, isGridVisible)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentPixel = pixels[position]
        if (currentPixel.parent != null) {
            (currentPixel.parent as ViewGroup).removeView(currentPixel) // <- fix
        }
        holder.tileParent.addView(currentPixel)

        holder.tileParent.setOnTouchListener (object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event?.action == MotionEvent.ACTION_DOWN){
                    caller.onPixelTapped(currentPixel)
                    return true
                }
                return false
            }
        })
    }

    override fun getItemCount() = pixels.size
}