package com.example.data.repositories

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.repositories.SharedPreferenceRepository

class SharedPreferenceRepositoryImpl constructor(
    private val context: Context
) : SharedPreferenceRepository {
    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            KEY_PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    override var savedPlaceId: String
        get() = getValue(KEY_APP_SAVED_PLACE_ID, "")
        set(value) {
            setValue(KEY_APP_SAVED_PLACE_ID, value)
        }

    private inline fun <reified T> getValue(key: String, defaultValue: T): T =
        with(prefs.value) {
            when (T::class) {
                String::class -> getString(key, defaultValue?.toString())
                Boolean::class -> getBoolean(key, (defaultValue as? Boolean) ?: false)
                Float::class -> getFloat(key, (defaultValue as? Float) ?: 0f)
                Int::class -> getInt(key, (defaultValue as? Int) ?: 0)
                Long::class -> getLong(key, (defaultValue as? Long) ?: 0L)
                else -> null
            }
        } as T

    private fun setValue(key: String, value: Any?) {
        prefs.value.edit().apply {
            when (value) {
                null -> putString(key, null)
                is String -> putString(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
            }
            apply()
        }
    }

    companion object {
        private const val KEY_PREFS_NAME = "key_weather_app_prefs"
        private const val KEY_APP_SAVED_PLACE_ID = "app_saved_place"
    }
}