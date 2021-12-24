package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding

fun CanvasActivity.setBindings() {
    binding = ActivityCanvasBinding.inflate(layoutInflater)
    setContentView(binding.root)
}