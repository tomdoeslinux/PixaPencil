package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding

fun MainActivity.extendedSetBindings() {
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
}