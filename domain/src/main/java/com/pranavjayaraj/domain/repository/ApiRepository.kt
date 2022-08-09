package com.pranavjayaraj.domain.repository

import com.pranavjayaraj.domain.models.containerModels.ContainerRemoteResModel
import io.reactivex.Single


interface ApiRepository {
    fun getContainer(): Single<ContainerRemoteResModel>
}