package com.pranavjayaraj.widget.containerview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleObserver
import com.pranavjayaraj.R
import com.pranavjayaraj.widget.containerview.viewModels.ContainerViewModel

class ContainerView : FrameLayout, LifecycleObserver {

    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        val view: View = LayoutInflater.from(context).inflate(R.layout.view_container, this);
        mContext = context
        setupUI(view)
    }

    constructor(context: Context, attr: AttributeSet? = null) : super(context, attr) {
        val view: View = LayoutInflater.from(context).inflate(R.layout.view_container, this);
        mContext = context
        setupUI(view)
    }

    constructor(context: Context, attr: AttributeSet? = null, defStyle: Int) : super(
        context,
        attr,
        defStyle
    ) {
        val view: View = LayoutInflater.from(context).inflate(R.layout.view_container, this);
        mContext = context
        setupUI(view)
    }

    fun setupUI(view: View)
    {

    }

    fun setupData(containerViewModel: ContainerViewModel)
    {
        containerViewModel.getContainer()
    }

}