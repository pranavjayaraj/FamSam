package com.pranavjayaraj.domain.types

interface UseCaseWithParams<T, R> {
    fun execute(param: T): R
}