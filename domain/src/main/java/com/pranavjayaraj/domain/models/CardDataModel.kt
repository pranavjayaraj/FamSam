package com.pranavjayaraj.domain.models

import com.google.gson.annotations.SerializedName

data class CardDataModel(
    @SerializedName("name")
    val name:String,
    @SerializedName("title")
    val title:String
)