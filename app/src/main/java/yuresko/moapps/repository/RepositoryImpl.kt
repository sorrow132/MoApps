package yuresko.moapps.repository

import io.reactivex.Single
import yuresko.moapps.core.AuthenticationResource
import yuresko.moapps.network.ApiService
import yuresko.moapps.network.model.LoginResponse
import yuresko.moapps.network.model.RawUserAppsModel
import yuresko.moapps.network.model.RawUserInfoModel
import yuresko.moapps.network.model.UserAppsResponse

interface IRepository {

    fun authentication(
        userName: String,
        password: String
    ): Single<AuthenticationResource<LoginResponse>>

    fun getUserName(): String

    fun getUserToken(): String

    fun getUserApps(userToken: String): Single<UserAppsResponse>
}

class RepositoryImpl(private val apiService: ApiService) : IRepository {

    private var actualLog: String = ""
    private var actualToken: String = ""

    override fun authentication(
        userName: String,
        password: String
    ): Single<AuthenticationResource<LoginResponse>> {
        actualLog = userName

        return apiService
            .authentication(RawUserInfoModel(userNick = userName, password = password))
            .map<AuthenticationResource<LoginResponse>> {
                actualToken = it.data
                AuthenticationResource.Data(it)

            }
            .onErrorReturn {
                AuthenticationResource.Error(it)
            }
    }

    override fun getUserName(): String {
        return actualLog
    }

    override fun getUserApps(userToken: String): Single<UserAppsResponse> {

        return apiService
            .getApps(
                RawUserAppsModel(
                    skip = 0,
                    take = 1000,
                    osType = 0,
                    userToken = userToken
                )
            )
    }

    override fun getUserToken(): String {
        return actualToken
    }
}