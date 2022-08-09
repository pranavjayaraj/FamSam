package com.pranavjayaraj.data.services

import com.pranavjayaraj.domain.models.containerModels.ContainerRemoteResModel
import com.pranavjayaraj.domain.repository.ApiRepository
import io.reactivex.Single
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val containerAPI: ContainerAPI
    ) : ApiRepository {
    override fun getContainer(): Single<ContainerRemoteResModel> {
        return containerAPI.getContainer()
    }

}