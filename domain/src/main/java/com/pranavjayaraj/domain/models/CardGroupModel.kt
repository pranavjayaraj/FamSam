package com.pranavjayaraj.domain.models

import com.google.gson.annotations.SerializedName

data class CardGroupModel(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("design_type")
    val designType: String,
    @SerializedName("cards")
    val cardDataModel: CardDataModel
)