package yuresko.moapps.mainview.model

data class ItemModel(
    val image: String,
    val appName: String,
    val isPaid: Boolean,
    val incomplete: Boolean,
    val appLink: String
)