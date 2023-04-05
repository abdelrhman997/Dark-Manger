package com.example.darkmanger.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.text.SimpleDateFormat
import java.util.*

class extentions {
    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    fun WebView.loadUrl(url: String?) {
        if (!url.isNullOrEmpty()) {
            loadUrl(url)
        }
    }
    fun Context.isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }
    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    fun Date.getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return dateFormat.format(this)
    }
    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    inline fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        val value = when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1L) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            else -> throw IllegalArgumentException("Unsupported type: ${T::class.java}")
        }
        return value
    }

    inline fun <reified T : Any> SharedPreferences.put(key: String, value: T?) {
        val editor = edit()
        when (T::class) {
            String::class -> editor.putString(key, value as? String)
            Int::class -> editor.putInt(key, value as? Int ?: -1)
            Long::class -> editor.putLong(key, value as? Long ?: -1L)
            Float::class -> editor.putFloat(key, value as? Float ?: -1f)
            Boolean::class -> editor.putBoolean(key, value as? Boolean ?: false)
            else -> throw IllegalArgumentException("Unsupported type: ${T::class.java}")
        }
        editor.apply()
    }
}