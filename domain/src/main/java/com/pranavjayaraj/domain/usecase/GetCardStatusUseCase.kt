package com.pranavjayaraj.domain.usecase

import android.content.SharedPreferences
import com.pranavjayaraj.domain.models.containerModels.ContainerRemoteResModel
import com.pranavjayaraj.domain.repository.ApiRepository
import com.pranavjayaraj.domain.repository.PrefRepository
import com.pranavjayaraj.domain.types.SingleUseCase
import com.pranavjayaraj.domain.types.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCardStatusUseCase @Inject constructor(private val prefRepository: PrefRepository) :
    UseCase<String?> {
    override fun execute(): String? {
        return prefRepository.getCardStatus()
    }
}