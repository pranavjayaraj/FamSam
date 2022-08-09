package com.pranavjayaraj.base

import androidx.viewbinding.ViewBinding

abstract class BaseViewHolderVB<in BaseModel>(
    val binding: ViewBinding
) : BaseViewHolder<BaseModel>(binding.root)