package com.pranavjayaraj.base

import androidx.recyclerview.widget.RecyclerView
import com.pranavjayaraj.domain.models.containerModels.base.BaseModel
import com.pranavjayaraj.utils.isValidList

abstract class BaseAdapter<T : BaseViewHolder<BaseModel>> : RecyclerView.Adapter<T>() {
    open var listItems: MutableList<BaseModel> = mutableListOf()

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun addList(dataList: List<BaseModel>) {
        listItems = dataList.toMutableList()
        notifyDataSetChanged()
    }

    fun updateList(dataList: List<BaseModel>) {
        listItems.addAll(dataList.toMutableList())
        notifyDataSetChanged()
    }

    fun updateList(dataItem: BaseModel) {
        listItems.add(dataItem)
        notifyItemInserted(listItems.size - 1);
    }

    fun clearList() {
        listItems.clear()
        notifyDataSetChanged()
    }

    open fun getItemAtPos(position: Int): BaseModel {
        return listItems[position]
    }

    fun updateItemChanged(dataList: List<BaseModel>, itemIndex: Int, payloadInt: Int = -1) {
        if (listItems.isValidList(itemIndex) && dataList.isValidList(itemIndex)) {
            listItems[itemIndex] = dataList[itemIndex]
            if (payloadInt != -1) notifyItemChanged(itemIndex, payloadInt)
            else notifyItemChanged(itemIndex)
        }
    }

    fun updateItemChanged(data: BaseModel, itemIndex: Int) {
        if (listItems.isValidList(itemIndex)) {
            listItems[itemIndex] = data
            notifyItemChanged(itemIndex)
        }
    }

    fun updateItemRange(dataList: List<BaseModel>) {
        val size = listItems.size
        this.listItems.addAll(listItems)
        val sizeNew = dataList.size
        notifyItemRangeChanged(size, sizeNew)
    }

    fun insertItemAt(data: BaseModel, index: Int) {
        listItems.add(index, data)
        notifyItemInserted(index)
    }

    fun removeItemAt(position: Int) {
        if (listItems.isValidList(position)) {
            listItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        bindVH(holder, position)
    }

    override fun onBindViewHolder(holder: T, position: Int, payloads: MutableList<Any>) {
        bindVH(holder, position, if (payloads.isEmpty()) VHUpdateType.DEFAULT else VHUpdateType.PARTIAL)
    }

    abstract fun bindVH(holder: T, position: Int, payload: VHUpdateType = VHUpdateType.DEFAULT)

    fun updateListItemChangedPartially(position: Int, payload: VHUpdateType = VHUpdateType.PARTIAL) {
        notifyItemChanged(position, payload)
    }

    fun updateItemChangedPartially(data: BaseModel, position: Int, payload: VHUpdateType = VHUpdateType.PARTIAL) {
        if (!listItems.isValidList(position)) return

        listItems[position] = data

        when (payload) {
            VHUpdateType.PARTIAL -> notifyItemChanged(position, payload)
            else -> notifyItemChanged(position)
        }
    }
}