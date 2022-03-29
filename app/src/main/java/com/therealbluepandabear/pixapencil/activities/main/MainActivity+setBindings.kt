package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.databinding.ActivityMainBinding

fun MainActivity.setBindings() {
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
}