package com.pranavjayaraj.data.services

import com.pranavjayaraj.domain.models.containerModels.ContainerRemoteResModel
import io.reactivex.Single
import retrofit2.http.*

interface ContainerAPI {
    @GET(Urls.GET_CONTAINER)
    fun getContainer(): Single<ContainerRemoteResModel>

}