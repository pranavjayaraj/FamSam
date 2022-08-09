package com.pranavjayaraj.domain.types

interface UseCase<R> {
    fun execute(): R
}