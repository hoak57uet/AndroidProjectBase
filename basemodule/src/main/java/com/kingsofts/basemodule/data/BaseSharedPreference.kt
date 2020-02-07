package com.kingsofts.basemodule.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.kingsofts.basemodule.base.impl.BaseApplication

/**
 * Manage preference
 */
class BaseSharedPreference {
    companion object {
        var preference: SharedPreferences? = null
        private fun instance(context: Context): SharedPreferences {
            if (preference == null) {
                preference = context.getSharedPreferences(
                    "pref_${context.packageName}",
                    Context.MODE_PRIVATE
                )
            }
            return preference!!
        }

        public fun put(key: String, data: String) {
            var editor = instance(BaseApplication.Instance).edit()
            editor.putString(key, data)
            editor.commit()
        }

        public fun putObject(key: String, data: Any?) {
            if (data == null)
                return
            var editor = instance(BaseApplication.Instance).edit()
            editor.putString(key, Gson().toJson(data))
            editor.commit()
        }

        public fun <T> getObject(key: String, clazz: Class<T>): T? {
            var stringData = instance(BaseApplication.Instance).getString(key, null)
            if (stringData.isNullOrBlank())
                return null
            return Gson().fromJson<T>(stringData, clazz)
        }

    }
}