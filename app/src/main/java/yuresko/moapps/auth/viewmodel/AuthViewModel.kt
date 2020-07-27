package yuresko.moapps.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import yuresko.moapps.core.AuthenticationResource
import yuresko.moapps.core.SingleLiveEvent
import yuresko.moapps.core.base.BaseViewModel
import yuresko.moapps.repository.IRepository
import yuresko.moapps.utils.addTo

interface IAuthViewModel {

    val isLoading: LiveData<Boolean>

    val isErrorResponse: LiveData<Boolean>

    val error: LiveData<Throwable>

    val openNextScreen: LiveData<String>

    fun authentication(userName: String, password: String)

}

class AuthViewModel(private val repository: IRepository) : BaseViewModel(), IAuthViewModel {

    override val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    override val isErrorResponse: MutableLiveData<Boolean> = MutableLiveData(false)

    override val error: SingleLiveEvent<Throwable> = SingleLiveEvent()

    override val openNextScreen: SingleLiveEvent<String> = SingleLiveEvent()

    override fun authentication(userName: String, password: String) {
        repository
            .authentication(userName, password)
            .toObservable()
            .startWith(AuthenticationResource.Loading())
            .subscribeOn(Schedulers.io())
            .subscribe { resource ->
                when (resource) {
                    is AuthenticationResource.Loading -> {
                        isLoading.postValue(true)
                    }
                    is AuthenticationResource.Data -> {
                        if (!resource.data.data.isNullOrEmpty()) {
                            isErrorResponse.postValue(false)
                            isLoading.postValue(false)
                            openNextScreen.postValue(repository.getUserName())
                        } else {
                            isErrorResponse.postValue(true)
                            isLoading.postValue(false)
                        }
                    }
                    is AuthenticationResource.Error -> {
                        isLoading.postValue(false)
                        error.postValue(resource.error)
                    }
                }
            }
            .addTo(compositeDisposable)
    }
}