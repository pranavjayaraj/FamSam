package com.pranavjayaraj.data

import com.pranavjayaraj.domain.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchedulersFacade @Inject constructor() : SchedulerProvider {
    private val core = Runtime.getRuntime().availableProcessors()
    private val executorsPool = ThreadPoolExecutor(
        0, core,
        5L, TimeUnit.SECONDS, LinkedBlockingQueue<Runnable>()
    )

    override fun ioPool(): Scheduler {
        return Schedulers.from(executorsPool)
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}