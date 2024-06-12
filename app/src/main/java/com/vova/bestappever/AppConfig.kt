package com.vova.bestappever

import android.content.Context
import androidx.core.content.edit

class AppConfig(
    private val applicationContext: Context
) {
    private val prefsName = "appConfig"
    private val userIdKey = "userIdKey"
    private val userEmailKey = "userEmailKey"
    private val tokenKey = "tokenKey"

    private val sp = applicationContext.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    fun getUserId(): Int? {
        val userId = sp.getInt(userIdKey, -1)

        return if(userId == -1) null else userId
    }

    fun setUserId(id: Int) {
        sp.edit {
            putInt(userIdKey, id)
            apply()
        }
    }

    fun getUserEmail(): String? {
        val userEmail = sp.getString(userEmailKey, "")

        return if(userEmail == "") null else userEmail
    }

    fun setUserEmail(email: String) {
        sp.edit {
            putString(userEmailKey, email)
            apply()
        }
    }

    fun getToken(): String? {
        val res = sp.getString(tokenKey, "")
        return if(res == "") null else res
    }

    fun setToken(token: String) {
        sp.edit {
            putString(tokenKey, token)
            apply()
        }
    }
}