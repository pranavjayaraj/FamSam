package com.pranavjayaraj.domain.usecase

import com.pranavjayaraj.domain.models.containerModels.ContainerRemoteResModel
import com.pranavjayaraj.domain.repository.ApiRepository
import com.pranavjayaraj.domain.types.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetContainerUseCase @Inject constructor(private val apiRepository: ApiRepository) :
    SingleUseCase<ContainerRemoteResModel> {
    override fun execute(): Single<ContainerRemoteResModel> {
        return apiRepository.getContainer()
    }
}