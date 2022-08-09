package com.pranavjayaraj.domain.repository

import io.reactivex.Completable
import io.reactivex.Single

interface PrefRepository {
    fun getCardStatus(): String?
    fun setCardStatus(Single:String):Completable
}