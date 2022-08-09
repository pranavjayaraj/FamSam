package com.pranavjayaraj.domain.usecase

import com.pranavjayaraj.domain.repository.PrefRepository
import com.pranavjayaraj.domain.types.UseCase
import com.pranavjayaraj.domain.types.UseCaseWithParams
import io.reactivex.Completable
import javax.inject.Inject

class SetCardStatusUseCase @Inject constructor(private val prefRepository: PrefRepository) :
    UseCaseWithParams<String,Completable> {
    override fun execute(status:String): Completable {
        return prefRepository.setCardStatus(status)
    }
}