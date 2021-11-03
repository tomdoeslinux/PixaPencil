package com.realtomjoney.pyxlmoose.database

interface Database<T> {
    fun toList(): List<T>
    fun addItem(item: T)
    fun removeItem(item: T)
}