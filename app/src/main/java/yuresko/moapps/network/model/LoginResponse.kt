package yuresko.moapps.network.model

data class LoginResponse(
    val data: String,
    val err: Boolean,
    val code: Int
) {
    fun getToken(): String{
        return data
    }
}