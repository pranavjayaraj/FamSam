package com.pranavjayaraj.domain.models

import com.google.gson.annotations.SerializedName

data class ContainerRemoteResModel(
    @SerializedName("card_groups")
    val cardGroups: ArrayList<CardGroupModel>
)