package com.pranavjayaraj.widget.containerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pranavjayaraj.base.BaseAdapter
import com.pranavjayaraj.base.BaseViewHolder
import com.pranavjayaraj.base.VHUpdateType
import com.pranavjayaraj.databinding.*
import com.pranavjayaraj.domain.models.containerModels.CardDataModel
import com.pranavjayaraj.domain.models.containerModels.CardGroupModel
import com.pranavjayaraj.domain.models.containerModels.base.BaseModel
import com.pranavjayaraj.widget.containerview.Constants

class ContainerItemsAdapter(private val cardDesignType:String) : BaseAdapter<BaseViewHolder<BaseModel>>() {

    override fun bindVH(holder: BaseViewHolder<BaseModel>, position: Int, payload: VHUpdateType) {
        if (payload == VHUpdateType.DEFAULT) {
            holder.bindData(getItemAtPos(position))
        } else {
            holder.bindDataWithPayload(getItemAtPos(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseModel> {
        return when (viewType) {

            Constants.SMALL_DISPLAY_CARD.ordinal -> SmallDisplayCardVH(
                ItemSmallDisplayCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            Constants.BIG_DISPLAY_CARD.ordinal -> BigDisplayCardVH(
                ItemBigDisplayCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            Constants.IMAGE_CARD.ordinal -> ImageCardVH(
                ItemImageCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            Constants.SMALL_CARD_WITH_ARROW.ordinal -> SmallCardWithArrowVH(
                ItemSmallCardArrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            Constants.DYNAMIC_WIDTH_CARD.ordinal -> {
                DynamicWidthCardVH(
                    ItemDynamicWidthCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else ->
            {
                SmallDisplayCardVH(
                    ItemSmallDisplayCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val model = getItemAtPos(position)
        model as CardDataModel
        return Constants.get(cardDesignType)?.ordinal ?: -1
    }


    inner class SmallDisplayCardVH(private val binding: ItemSmallDisplayCardBinding):BaseViewHolder<BaseModel>(binding.root){
        override fun bindData(model: BaseModel) {
        }
    }

    inner class BigDisplayCardVH(private val binding:ItemBigDisplayCardBinding):BaseViewHolder<BaseModel>(binding.root){
        override fun bindData(model: BaseModel) {
        }

    }

    inner class ImageCardVH(private val binding: ItemImageCardBinding) :BaseViewHolder<BaseModel>(binding.root){
        override fun bindData(model: BaseModel) {
        }

    }

    inner class SmallCardWithArrowVH(private val binding:ItemSmallCardArrowBinding) :BaseViewHolder<BaseModel>(binding.root){
        override fun bindData(model: BaseModel) {
        }

    }

    inner class DynamicWidthCardVH(private val binding:ItemDynamicWidthCardBinding) :BaseViewHolder<BaseModel>(binding.root){
        override fun bindData(model: BaseModel) {
        }

    }
}


