package com.pranavjayaraj.domain.models.containerModels

import com.google.gson.annotations.SerializedName
import com.pranavjayaraj.domain.models.containerModels.base.BaseModel

data class ContainerRemoteResModel(
    @SerializedName("card_groups")
    val cardGroups: ArrayList<CardGroupModel>
)

data class CardGroupModel(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("design_type")
    val designType: String,
    @SerializedName("cards")
    val cardDataModel: ArrayList<CardDataModel>,
    @SerializedName("is_scrollable")
    val isScrollable:Boolean
):BaseModel

data class CardDataModel(
    @SerializedName("name")
    val name:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("formatted_title")
    val formattedTitles:FormattedTitles,
    @SerializedName("description")
    val description:String,
    @SerializedName("formatted_description")
    val formattedDescription:FormattedTitles,
    @SerializedName("icon")
    val icon:IconModel,
    @SerializedName("url")
    val url:String
):BaseModel

data class FormattedTitles(
    @SerializedName("text")
    val text:String,
    @SerializedName("entities")
    val entities:ArrayList<EntitiesModel>
)

data class EntitiesModel(
    @SerializedName("text")
    val text: String,
    @SerializedName("color")
    val color: String
)

data class IconModel(
    @SerializedName("image_type")
    val imageType:String,
    @SerializedName("image_url")
    val imageUrl:String
)

