package yuresko.moapps.mainview.model

data class MainMenuModel(
    val image: String,
    val appName: String,
    val isPaid: Boolean,
    val incomplete: Boolean
)