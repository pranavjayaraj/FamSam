package com.pranavjayaraj.base

interface BaseAdapterItemClick<T> {

    fun onItemClick(viewType: Int, data:T){

    }

    fun onItemClick(viewType: Int, data:T, position:Int)
    {

    }

    fun onInnerItemClick(viewType: Int, data:T, position: Int, innerData: Any){

    }

    fun onInnerItemClick(viewType: Int, data:T, parentItemPosition: Int, innerData: Any, childItemPosition:Int){

    }

}