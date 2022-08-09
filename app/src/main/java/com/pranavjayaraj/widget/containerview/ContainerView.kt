package com.pranavjayaraj.widget.containerview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pranavjayaraj.R
import com.pranavjayaraj.domain.models.containerModels.CardGroupModel
import com.pranavjayaraj.utils.getLifecycleOwner
import com.pranavjayaraj.widget.containerview.adapters.ContainerAdapter
import com.pranavjayaraj.widget.containerview.viewModels.ContainerViewModel

class ContainerView : FrameLayout, LifecycleObserver {

    private var mContext: Context? = null

    lateinit var mContainerAdapter:ContainerAdapter

    private var mContainerRecyclerView: RecyclerView? = null

    lateinit var viewModel : ContainerViewModel

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

    private fun setupUI(view: View)
    {
        mContainerRecyclerView = view.findViewById<View>(R.id.rvContainer) as RecyclerView
        setupAdapter()
    }

    fun setupData(containerViewModel: ContainerViewModel)
    {
        viewModel = containerViewModel
        viewModel.getContainer()
        observeEvents()
    }

    private fun setupAdapter()
    {
        mContainerAdapter = ContainerAdapter()
        mContainerRecyclerView?.layoutManager = LinearLayoutManager(
            mContext, RecyclerView.VERTICAL, false
        )
        mContainerRecyclerView?.adapter = mContainerAdapter
    }

    private fun observeEvents() {
        viewModel.containerLiveData.observe(context.getLifecycleOwner(), {
            val list:List<CardGroupModel> = it.cardGroups
            mContainerAdapter.addList(list)
        })
    }

}