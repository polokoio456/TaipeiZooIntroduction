package com.nie.taipeizoo.extension

import com.google.gson.Gson

fun Any.toJson(): String {
    return Gson().toJson(this)
}

fun <T> String.toObject(clazz: Class<T>): T {
    return Gson().fromJson(this, clazz)
}