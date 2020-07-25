package yuresko.moapps.mainview.model

sealed class MainMenuState {

    object Loading : MainMenuState()

    object EmptyInfo : MainMenuState()

    data class LoadInfo(
        val appsInfo: List<MainMenuModel>
    ) : MainMenuState()

    data class Error(
        val error: Throwable
    ) : MainMenuState()
}