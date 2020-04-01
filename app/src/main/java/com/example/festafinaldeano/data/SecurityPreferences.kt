package com.example.festafinaldeano.data

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(mContext: Context) {

    private var _SharedPreferences: SharedPreferences

    init {
        /*
        * Estou dizendo que meu SharedPreferences e privado, então ninguém fora da aplicação
        * poderá acessálo!
        * */
        _SharedPreferences = mContext.getSharedPreferences("FestaFimAno", Context.MODE_PRIVATE)
    }

    fun storeString(key: String, value: String) {
        _SharedPreferences.edit().putString(key, value).apply()
    }

    fun getStoreString(key: String): String {
        return _SharedPreferences.getString(key, "")!!
    }
}