package com.pranavjayaraj.ui.viewModels

import com.pranavjayaraj.base.BaseViewModel
import com.pranavjayaraj.data.SchedulersFacade
import com.pranavjayaraj.domain.SchedulerProvider
import com.pranavjayaraj.domain.usecase.GetContainerUseCase
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    schedulersFacade: SchedulerProvider,
    private val getContainerUseCase: GetContainerUseCase
) : BaseViewModel(schedulersFacade) {

    fun getContainer() {
        getContainerUseCase.execute()
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
            .subscribe({
                Timber.d("[DATA]"+it)
            }, {

            })
    }

}