package com.pranavjayaraj.domain.types

import io.reactivex.Completable

interface CompletableUseCase {
    fun execute():Completable
}