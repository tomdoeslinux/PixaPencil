package com.realtomjoney.pyxlmoose

interface Database<T> {
    fun toList(): List<T>
    fun addItem(item: T)
    fun removeItem(item: T)
}