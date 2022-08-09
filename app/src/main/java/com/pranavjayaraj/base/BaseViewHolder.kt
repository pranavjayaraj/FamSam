package com.pranavjayaraj.base

import android.view.View
import android.view.ViewGroup
import com.pranavjayaraj.utils.inflate


abstract class BaseViewHolder<T>(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view), View.OnClickListener{

    var adapterCallback: BaseAdapterItemClick<T>?=null
    var currPosition: Int = -1
    open fun setData(data: T){
        setData(data, null)
    }
    open fun setData(data: T, payload: MutableList<Any>?){

    }

    fun setPosition(position: Int){
        currPosition = position
    }

    fun setCallbackListener(callback: BaseAdapterItemClick<T>?) {
        adapterCallback = callback
    }


    override fun onClick(clickedView: View?) {

    }

    companion object {
        fun getView(parent: ViewGroup, resId: Int) = parent.inflate(resId)
    }
}