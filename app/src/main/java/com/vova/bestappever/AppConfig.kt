package com.vova.bestappever

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.vova.bestappever.data.models.User

class AppConfig(
    private val applicationContext: Context
) {
    private val prefsName = "appConfig"
    private val userKey = "userKey"

    private val sp = applicationContext.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    fun getCurrentUserId(): Int? {
        val userId = sp.getInt(userKey, -1)

        return if(userId == -1) null else userId
    }

    fun setCurrentUserId(id: Int) {
        sp.edit {
            putInt(userKey, id)
            apply()
        }
    }
}