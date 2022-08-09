package com.pranavjayaraj.domain.types

import io.reactivex.Single

interface SingleUseCaseWithParam<P,R> {
    fun execute(param:P):Single<R>
}