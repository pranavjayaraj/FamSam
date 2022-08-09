package com.pranavjayaraj.widget.containerview.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pranavjayaraj.base.BaseViewModel
import com.pranavjayaraj.domain.SchedulerProvider
import com.pranavjayaraj.domain.models.containerModels.ContainerRemoteResModel
import com.pranavjayaraj.domain.usecase.GetContainerUseCase
import timber.log.Timber
import javax.inject.Inject

class ContainerViewModel @Inject constructor(
    schedulersFacade: SchedulerProvider,
    private val getContainerUseCase: GetContainerUseCase
) : BaseViewModel(schedulersFacade) {

    val containerLiveData  = MutableLiveData<ContainerRemoteResModel>()

    fun getContainer() {
        getContainerUseCase.execute()
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
            .subscribe({
                containerLiveData.postValue(it)
                Log.d("[SUCCESS]","DATA IS"+it)
                Timber.d("[DATA IS]"+it)
            }, {
                Log.d("[FAILED]","DATA IS"+it)
            })
    }

}