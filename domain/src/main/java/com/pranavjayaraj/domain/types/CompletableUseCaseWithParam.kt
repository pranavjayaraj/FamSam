package com.pranavjayaraj.domain.types

import io.reactivex.Completable

interface CompletableUseCaseWithParam<P>{
    fun execute(param:P):Completable
}