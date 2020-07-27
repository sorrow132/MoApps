package yuresko.moapps.utils

import android.content.SharedPreferences
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun View.visibleOrGone(isVisible: Boolean) {
    if (isVisible) {
        visibility = View.VISIBLE
    } else {
        visibility = View.GONE
    }
}

fun getUserToken(sharedPreferences: SharedPreferences): String {
    return sharedPreferences.getString("user_token", "0").toString()
}