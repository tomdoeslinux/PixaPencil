package com.realtomjoney.pyxlmoose.activity.canvas

import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding

fun CanvasActivity.extendedSetBindings() {
    binding = ActivityCanvasBinding.inflate(layoutInflater)
    setContentView(binding.root)
}