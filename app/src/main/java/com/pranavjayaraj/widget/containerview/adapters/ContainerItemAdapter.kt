package com.pranavjayaraj.widget.containerview.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*;
import com.pranavjayaraj.utils.*;
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.pranavjayaraj.R
import com.pranavjayaraj.base.BaseAdapter
import com.pranavjayaraj.base.BaseViewHolder
import com.pranavjayaraj.base.VHUpdateType
import com.pranavjayaraj.databinding.*
import com.pranavjayaraj.domain.models.containerModels.CardDataModel
import com.pranavjayaraj.domain.models.containerModels.CardGroupModel
import com.pranavjayaraj.domain.models.containerModels.base.BaseModel
import com.pranavjayaraj.utils.GlideDelegate
import com.pranavjayaraj.widget.containerview.Constants
import android.view.MotionEvent




class ContainerItemsAdapter(private val cardDesignType:String,private val height:Int) : BaseAdapter<BaseViewHolder<BaseModel>>() {

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
            if (model is CardDataModel) {
                with(binding)
                {
                    tvSmallCardTitle.text = model.title
                    binding.smallCardLayout.setCardBackgroundColor(Color.parseColor(model.bgColor?:"#FFFFFF"))
                    tvSmallCardDesc.text = model.description
                    GlideDelegate(itemView.context).loadUrlWithPlaceHolder(
                        ivSmallCardIcon,
                        model.icon?.imageUrl,
                        0
                    )
                }
            }
        }
    }

    inner class BigDisplayCardVH(private val binding:ItemBigDisplayCardBinding):BaseViewHolder<BaseModel>(binding.root){
        override fun bindData(model: BaseModel) {
            if (model is CardDataModel) {
                with(binding)
                {
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(root)
                    constraintSet.setDimensionRatio(R.id.ivCard,model.bgImageModel?.aspectRatio)
                    constraintSet.applyTo(root)
                    GlideDelegate(itemView.context).loadUrlWithPlaceHolder(ivCard,model.bgImageModel?.bgImage,0)
                }
                binding.ivCard.setOnLongClickListener {
                    it.animate()
                        .x(114.dpToPx())
                        .setDuration(0)
                        .start();
                    true
                }
            }
        }

    }

    inner class ImageCardVH(private val binding: ItemImageCardBinding) :BaseViewHolder<BaseModel>(binding.root){
        override fun bindData(model: BaseModel) {
            if (model is CardDataModel) {
                with(binding)
                {
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(root)
                    constraintSet.setDimensionRatio(R.id.ivImageCard,model.bgImageModel?.aspectRatio)
                    constraintSet.applyTo(root)
                    GlideDelegate(itemView.context).loadUrlWithPlaceHolder(ivImageCard,model.bgImageModel?.bgImage,0)
                }
            }
        }

    }

    inner class SmallCardWithArrowVH(private val binding: ItemSmallCardArrowBinding) :
        BaseViewHolder<BaseModel>(binding.root) {
        override fun bindData(model: BaseModel) {
            if (model is CardDataModel) {
                with(binding)
                {
                    binding.smallCardArrowLayout.setCardBackgroundColor(Color.parseColor(model.bgColor?:"#FFFFFF"))
                    tvSmallCardArrowTitle.text = model.title
                    GlideDelegate(itemView.context).loadUrlWithPlaceHolder(
                        ivSmallCardArrowIcon,
                        model.icon?.imageUrl,
                        0
                    )
                }
            }
        }
    }

    inner class DynamicWidthCardVH(private val binding: ItemDynamicWidthCardBinding) :
        BaseViewHolder<BaseModel>(binding.root) {
        override fun bindData(model: BaseModel) {
            if (model is CardDataModel) {
                with(binding)
                {
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(root)
                    constraintSet.constrainHeight(R.id.ivDynamicCard,height.dpToPx().toInt())
                    constraintSet.setDimensionRatio(R.id.ivDynamicCard,model.bgImageModel?.aspectRatio)
                    constraintSet.applyTo(root)
                    GlideDelegate(itemView.context).loadUrlWithPlaceHolder(ivDynamicCard,model.bgImageModel?.bgImage,0)
                }
            }
        }

    }
}




