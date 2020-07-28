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