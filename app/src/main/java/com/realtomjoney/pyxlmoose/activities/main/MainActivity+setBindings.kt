package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding

fun MainActivity.setBindings() {
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
}