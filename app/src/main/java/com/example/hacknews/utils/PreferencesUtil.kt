package com.example.hacknews.utils

import android.content.Context

class PreferencesUtil(context: Context) {

    companion object {
        private const val TOPIC_SELECTION_SET_KEY = "topic_selection_set"
    }

    private val preferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    fun getSelectionTopicToken(): String? {
        return preferences.getString(TOPIC_SELECTION_SET_KEY, null)
    }

    fun setSelectionTopicToken(value: String?) {
        val editor = preferences.edit()
        editor.putString(TOPIC_SELECTION_SET_KEY, value)
        editor.apply()
    }
}