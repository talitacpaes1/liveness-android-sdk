package br.com.oititec.facecaptchasample.data

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences("certiface_sample_shared_preferences", Context.MODE_PRIVATE)
    }

    fun saveString(key: SharedPrefKey, value: String) {
        sharedPref.edit().putString(key.name, value).apply()
    }

    fun getString(key: SharedPrefKey): String? {
        return sharedPref.getString(key.name, null)
    }
}

enum class SharedPrefKey {
    BASE_URL,
    USERNAME,
    TOKEN,
    NAME,
    CPF,
    BIRTHDAY;
}
