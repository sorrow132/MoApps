package yuresko.moapps.core

sealed class UserApplicationResource<T> {
    class Loading<T> : UserApplicationResource<T>()

    data class Data<T>(val data: T) : UserApplicationResource<T>()

    data class Error<T>(val error: Throwable) : UserApplicationResource<T>()
}