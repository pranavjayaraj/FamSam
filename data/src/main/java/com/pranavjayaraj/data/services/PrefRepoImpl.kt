package com.pranavjayaraj.data.services

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pranavjayaraj.domain.repository.PrefRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PrefRepoImpl @Inject constructor(val context: Context, private val gson: Gson) :
    PrefRepository {

    private val sharedPreferenceHelper: SharedPreferenceManager =
        SharedPreferenceManager.getInstance(context)

    override fun getCardStatus(): String? {
        return sharedPreferenceHelper.getString("CONTAINER_CARD_STATUS")
    }

    override fun setCardStatus(map: String): Completable {
        return Completable.fromAction {
            sharedPreferenceHelper.setString(
                "CONTAINER_CARD_STATUS",
                map
            )
        }
    }
}
