package yuresko.moapps.mainview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import yuresko.moapps.core.base.BaseViewModel
import yuresko.moapps.mainview.model.ItemModel
import yuresko.moapps.mainview.model.MainMenuState
import yuresko.moapps.network.model.LoginResponse
import yuresko.moapps.repository.IRepository
import yuresko.moapps.utils.addTo

interface IMainMenuViewModel {

    val state: LiveData<MainMenuState>

    fun getAppsInfo(userToken: String?)
}

class MainMenuViewModel(private val repository: IRepository) :
    BaseViewModel(), IMainMenuViewModel {

    override val state: MutableLiveData<MainMenuState> = MutableLiveData()

    override fun getAppsInfo(userToken: String?) {
        repository
            .getUserApps(userToken)
            .map { list ->
                list.data.map { entity ->
                    ItemModel(
                        entity.applicationUrl,
                        entity.applicationName,
                        entity.isPayment,
                        entity.applicationStatus,
                        entity.applicationUrl
                    )
                }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                state.postValue(MainMenuState.Loading)
            }.subscribe({
                if (it.isEmpty()) {
                    state.postValue(MainMenuState.EmptyInfo)
                } else {
                    state.postValue(MainMenuState.LoadInfo(it))
                }
            }) {
                state.postValue(MainMenuState.Error(it))
            }
            .addTo(compositeDisposable)
    }
}