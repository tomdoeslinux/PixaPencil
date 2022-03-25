package com.therealbluepandabear.pyxlmoose.activities.main

import com.therealbluepandabear.pyxlmoose.databinding.ActivityMainBinding

fun MainActivity.setBindings() {
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
}