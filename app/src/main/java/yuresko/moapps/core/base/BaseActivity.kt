package yuresko.moapps.core.base

import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.IllegalArgumentException
import java.net.UnknownHostException

abstract class BaseActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    protected fun displayHostError(throwable: Throwable) {
        when (throwable) {
            is UnknownHostException -> {
                Toast.makeText(this, "Проверьте соединение с интернетом", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(
                    this,
                    throwable.message ?: "Произошла не предвиденная ошибка, попробуйте позже",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}