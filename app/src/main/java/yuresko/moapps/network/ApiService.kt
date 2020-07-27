package yuresko.moapps.network

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import yuresko.moapps.network.model.*

interface ApiService {

    @POST("Account/Login")
    fun authentication(@Body rawUserInfoModel: RawUserInfoModel): Single<LoginResponse>

    @POST("application")
    fun getApps(@Body rawUserAppsModel: RawUserAppsModel): Single<UserAppsResponse>

}