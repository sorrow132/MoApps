package yuresko.moapps.network.model

data class ErrorResponse (
    val data: String?,
    val err: Boolean,
    val code: Int
)