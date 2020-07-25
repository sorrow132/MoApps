package yuresko.moapps.repository

import io.reactivex.Single
import yuresko.moapps.core.AuthenticationResource
import yuresko.moapps.core.UserApplicationResource
import yuresko.moapps.network.ApiService
import yuresko.moapps.network.model.LogResponse
import yuresko.moapps.network.model.RawUserAppsModel
import yuresko.moapps.network.model.RawUserInfoModel
import yuresko.moapps.network.model.UserAppsResponse

interface IRepository {

    fun authentication(
        userName: String,
        password: String
    ): Single<AuthenticationResource<LogResponse>>

    fun getUserName(): String

    fun getUserToken(): String

    fun getUserApps(): Single<UserAppsResponse>
}

class RepositoryImpl(private val apiService: ApiService) : IRepository {

    private var actualLog: String = ""
    private var actualToken: String = ""

    override fun authentication(
        userName: String,
        password: String
    ): Single<AuthenticationResource<LogResponse>> {
        actualLog = userName
        return apiService
            .authentication(RawUserInfoModel(userNick = userName, password = password))
            .map<AuthenticationResource<LogResponse>> {
                AuthenticationResource.Data(it)
            }
            .onErrorReturn {
                AuthenticationResource.Error(it)
            }
    }

    override fun getUserName(): String {
        return actualLog
    }

    override fun getUserApps(): Single<UserAppsResponse> {
        return apiService
            .getApps(
                RawUserAppsModel(
                    skip = 0,
                    take = 1000,
                    osType = 0,
                    userToken = getUserToken()
                )
            )
    }

    override fun getUserToken(): String {
        return actualToken
    }
}