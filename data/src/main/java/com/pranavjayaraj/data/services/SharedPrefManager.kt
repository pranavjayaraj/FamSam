package com.pranavjayaraj.data.services

import android.content.Context
import android.content.SharedPreferences
import com.pranavjayaraj.data.BuildConfig

class SharedPreferenceManager private constructor(appContext: Context) {

    private var prefs: SharedPreferences
    private val prefName = "Prefs"

    init {
        prefs = appContext.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    companion object {
        private var instance: SharedPreferenceManager? = null
        @Synchronized
        fun getInstance(appContext: Context): SharedPreferenceManager {
            if (instance == null) {
                instance = SharedPreferenceManager(appContext)
            }
            return instance!!
        }
    }
    fun getString(key: String, defValue: String?): String? = prefs.getString(key, defValue)

    fun getString(key: String): String? = getString(key,"")

    fun setString(key: String, value: String?) {
        prefs.edit().run {
            putString(key, value)
            commit()
        }
    }

    private fun getBoolean(key: String, defValue: Boolean): Boolean? = prefs.getBoolean(key, defValue)

    fun getBoolean(key: String): Boolean? = getBoolean(key, false)

    fun setBoolean(key: String, value: Boolean) {
        prefs.edit().run {
            putBoolean(key, value)
            commit()
        }
    }
}