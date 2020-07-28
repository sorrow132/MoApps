package yuresko.moapps.utils

import android.content.SharedPreferences
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import yuresko.moapps.repository.IRepository

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

fun getUserToken(sharedPreferences: SharedPreferences, repository: IRepository): String {
    return sharedPreferences.getString("user_token", repository.getUserToken()).toString()
}