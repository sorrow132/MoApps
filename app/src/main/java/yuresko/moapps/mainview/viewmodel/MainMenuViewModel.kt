package yuresko.moapps.mainview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import yuresko.moapps.core.base.BaseViewModel
import yuresko.moapps.mainview.model.MainMenuModel
import yuresko.moapps.mainview.model.MainMenuState
import yuresko.moapps.repository.IRepository
import yuresko.moapps.utils.addTo

interface IMainMenuViewModel {

    val state: LiveData<MainMenuState>

    fun getAppsInfo()
}

class MainMenuViewModel(private val repository: IRepository) : BaseViewModel(), IMainMenuViewModel {

    override val state: MutableLiveData<MainMenuState> = MutableLiveData()

    init {
        getAppsInfo()
    }

    override fun getAppsInfo() {
        repository
            .getUserApps()
            .map { list ->
                list.listOfApps.map { entity ->
                    MainMenuModel(
                        entity.applicationUrl,
                        entity.applicationName,
                        entity.isPayment,
                        entity.applicationStatus
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