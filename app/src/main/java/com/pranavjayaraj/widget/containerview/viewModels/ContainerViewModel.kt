package com.pranavjayaraj.widget.containerview.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pranavjayaraj.base.BaseViewModel
import com.pranavjayaraj.domain.SchedulerProvider
import com.pranavjayaraj.domain.base.ResourceState
import com.pranavjayaraj.domain.models.containerModels.ContainerRemoteResModel
import com.pranavjayaraj.domain.usecase.GetCardStatusUseCase
import com.pranavjayaraj.domain.usecase.GetContainerUseCase
import com.pranavjayaraj.domain.usecase.SetCardStatusUseCase
import timber.log.Timber
import javax.inject.Inject

class ContainerViewModel @Inject constructor(
    schedulersFacade: SchedulerProvider,
    private val getContainerUseCase: GetContainerUseCase,
    private val cardStatusUseCase: GetCardStatusUseCase,
    private val setCardStatusUseCase: SetCardStatusUseCase
) : BaseViewModel(schedulersFacade) {

    var isFirstTimeContainerCalled = true

    val containerLiveData = MutableLiveData<ResourceState<ContainerRemoteResModel>>()

    fun getContainer() {
        getContainerUseCase.execute()
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
            .subscribe({
                containerLiveData.postValue(ResourceState.Success(it))
            }, {
                containerLiveData.postValue(ResourceState.Failure(it))
            }).let {
                getCompositeDisposable().add(it)
            }
    }

    fun getCardStatus(): String? {
        return cardStatusUseCase.execute()
    }

    fun setCardStatus(status: String) {
        setCardStatusUseCase.execute(status)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({}, {

            }).let { }
    }
}