package com.pranavjayaraj.ui.viewModels

import com.pranavjayaraj.base.BaseViewModel
import com.pranavjayaraj.data.SchedulersFacade
import com.pranavjayaraj.domain.SchedulerProvider
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    schedulersFacade: SchedulerProvider
) : BaseViewModel(schedulersFacade) {

}