package com.pranavjayaraj.widget.containerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pranavjayaraj.R
import com.pranavjayaraj.base.BaseAdapter
import com.pranavjayaraj.base.BaseViewHolder
import com.pranavjayaraj.base.VHUpdateType
import com.pranavjayaraj.databinding.ItemContainerViewBinding
import com.pranavjayaraj.domain.models.containerModels.CardDataModel
import com.pranavjayaraj.domain.models.containerModels.CardGroupModel
import com.pranavjayaraj.domain.models.containerModels.base.BaseModel

class ContainerAdapter : BaseAdapter<BaseViewHolder<BaseModel>>() {


    override fun bindVH(holder: BaseViewHolder<BaseModel>, position: Int, payload: VHUpdateType) {
        holder.bindData(getItemAtPos(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseModel> {
        return ViewHolder(
            ItemContainerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ViewHolder(private val binding: ItemContainerViewBinding) :
        BaseViewHolder<BaseModel>(binding.root) {
        override fun bindData(model: BaseModel) {
            if (model is CardGroupModel) {
                val mContainerItemAdapter = ContainerItemsAdapter(model.designType?:"",model.height?:0)
                with(binding)
                {
                    if (model.isScrollable==true) {
                        rvItemContainer.layoutManager = LinearLayoutManager(
                            itemView.context, RecyclerView.HORIZONTAL, false
                        )
                    } else {
                        rvItemContainer.layoutManager = GridLayoutManager(
                            itemView.context, model.cardDataModel?.size?:0
                        )
                    }
                    rvItemContainer.adapter = mContainerItemAdapter
                    val list: List<CardDataModel> = model.cardDataModel?: emptyList()
                    mContainerItemAdapter.addList(list)
                }
            }
        }
    }

}