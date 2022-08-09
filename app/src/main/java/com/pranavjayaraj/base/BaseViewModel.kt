package com.pranavjayaraj.base

import androidx.lifecycle.ViewModel
import com.pranavjayaraj.domain.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @Inject constructor(
    val schedulers: SchedulerProvider
): ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun getCompositeDisposable():CompositeDisposable
    {
        return disposables
    }
}