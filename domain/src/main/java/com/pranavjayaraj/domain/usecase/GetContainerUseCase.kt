package com.pranavjayaraj.domain.usecase

import com.pranavjayaraj.domain.models.CardGroupModel
import com.pranavjayaraj.domain.models.ContainerRemoteResModel
import com.pranavjayaraj.domain.repository.ApiRepository
import com.pranavjayaraj.domain.types.SingleUseCase
import com.pranavjayaraj.domain.types.SingleUseCaseWithParam
import io.reactivex.Single
import javax.inject.Inject

class GetContainerUseCase @Inject constructor(private val apiRepository: ApiRepository) :
    SingleUseCase<ContainerRemoteResModel> {
    override fun execute(): Single<ContainerRemoteResModel> {
        return apiRepository.getContainer()
    }
}