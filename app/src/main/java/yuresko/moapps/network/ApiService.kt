package yuresko.moapps.network

import android.content.Context
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import yuresko.moapps.network.model.LoginResponse
import yuresko.moapps.network.model.RawUserAppsModel
import yuresko.moapps.network.model.RawUserInfoModel
import yuresko.moapps.network.model.UserAppsResponse

interface ApiService {

    @POST("Account/Login")
    fun authentication(@Body rawUserInfoModel: RawUserInfoModel): Single<LoginResponse>

    @POST("application")
    fun getApps(@Body rawUserAppsModel: RawUserAppsModel): Single<UserAppsResponse>

}

const val SHARED_PREF_NAME = "my_shared_preff"

class SharedPrefManager(private val context: Context) {

    fun saveData(data: LoginResponse) {
        val sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString("token", data.data)
        editor.apply()
    }

    fun loadData(data: LoginResponse): String? {
        val sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPrefs.getString(SHARED_PREF_NAME, "")
    }

    fun clear() {
        val sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
    }

}