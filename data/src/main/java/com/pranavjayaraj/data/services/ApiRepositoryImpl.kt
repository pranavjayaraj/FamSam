package com.pranavjayaraj.data.services

import com.pranavjayaraj.domain.repository.ApiRepository
import io.reactivex.Single
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    val apiService: ApiService
    ) : ApiRepository {

}