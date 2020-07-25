package yuresko.moapps.network.model

data class RawUserAppsModel(
    val skip: Int,
    val take: Int,
    val osType: Int,
    val userToken: String
)