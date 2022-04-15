package com.example.webviewstudy

import android.content.Context
import android.content.SharedPreferences

// SharedPreferences 를 간편하게 사용하기 위해 만든 클래스
class MyPreference(context: Context) {
    private val pref : SharedPreferences = context.getSharedPreferences("WebViewTest", Context.MODE_PRIVATE)

    fun getString(key: String, defaultValue: String) : String {
        return pref.getString(key, defaultValue).toString()
    }

    fun setString(key: String, str: String) {
        pref.edit().putString(key, str).apply()
    }

    companion object {
        const val ID = "ID"
        const val PW = "PASS_WORD"
    }
}