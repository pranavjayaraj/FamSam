package com.pranavjayaraj.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in BaseModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindData(model: BaseModel)
    open fun bindDataWithPayload(model: BaseModel, payload: VHUpdateType = VHUpdateType.PARTIAL) {}
}