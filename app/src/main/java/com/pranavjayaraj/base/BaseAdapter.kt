package com.pranavjayaraj.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<M, T:BaseViewHolder<M>>(private val callBack: BaseAdapterItemClick<M>?=null): RecyclerView.Adapter<T>() {
    private val itemList = ArrayList<M>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return getViewHolder(parent, viewType).apply {
            if (callBack != null) {
                setCallbackListener(callBack)
            }
        }
    }
    override fun onBindViewHolder(holder: T, position: Int, payloads: MutableList<Any>) {
        holder.setData(itemList[position], payloads)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        onBindViewHolder(holder, position, mutableListOf())
    }

    fun addItems(list: List<M>){
        itemList.clear()
        itemList.addAll(list)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): T
}