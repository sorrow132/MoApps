package yuresko.moapps.network.model

import com.google.gson.annotations.SerializedName

data class UserAppsResponse(
    val listOfApps: MutableList<UserAppInfo>
)

data class UserAppInfo(

    val applicationToken: String,

    val isPayment: Boolean,

    val applicationStatus: Boolean,

    val applicationName: String,

    val endOfPayment: String,

    val applicationIcoUrl: String,

    val applicationUrl: String,

    val applicationId: Int,

    @SerializedName("CountOrders")
    val countOrders: Int,

    @SerializedName("CountNewOrders")
    val countNewOrders: Int,

    @SerializedName("CountInProgressOders")
    val countInProgressOders: Int,

    @SerializedName("CountCompletedOrders")
    val countCompletedOrders: Int
)