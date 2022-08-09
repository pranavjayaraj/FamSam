package com.pranavjayaraj.widget.containerview

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.TimeFormatException
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pranavjayaraj.BaseAdapterItemClick
import com.pranavjayaraj.R
import com.pranavjayaraj.domain.models.containerModels.CardDataModel
import com.pranavjayaraj.domain.models.containerModels.CardGroupModel
import com.pranavjayaraj.domain.models.containerModels.base.BaseModel
import com.pranavjayaraj.ui.home.HomeActivity
import com.pranavjayaraj.utils.getLifecycleOwner
import com.pranavjayaraj.widget.containerview.adapters.ContainerAdapter
import com.pranavjayaraj.widget.containerview.viewModels.ContainerViewModel
import android.net.Uri




class ContainerView : FrameLayout, LifecycleObserver {

    private var mContext: Context? = null

    lateinit var mContainerAdapter:ContainerAdapter

    private var mContainerRecyclerView: RecyclerView? = null

    lateinit var viewModel : ContainerViewModel

    lateinit var cardList: List<CardGroupModel>

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
        viewModel.getCardStatus()
        viewModel.getContainer()
        observeEvents()
    }

    private val onItemClick = { card: CardDataModel ->
        if(card.viewType == "URL" || card.viewType == "CTA")
        {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(card.url?:card.ctaModel?.first()?.url))
            context.startActivity(intent)
        }
        else {
            when (card.cardType) {
                Constants.BIG_DISPLAY_CARD.key -> {
                    if (card.status in arrayOf(PARTIAL_GONE, PERMANENT_GONE)) {
                        setCardStatus(card.status)
                        mContainerAdapter.addList(cardList.filter { it.designType != Constants.BIG_DISPLAY_CARD.key })
                    }
                }
                Constants.SMALL_DISPLAY_CARD.key -> {

                }
                Constants.DYNAMIC_WIDTH_CARD.key -> {

                }
                Constants.SMALL_CARD_WITH_ARROW.key -> {

                }
                Constants.IMAGE_CARD.key -> {

                }
            }
        }
    }

    private fun setupAdapter()
    {
        mContainerAdapter = ContainerAdapter(onItemClick)

        mContainerRecyclerView?.layoutManager = LinearLayoutManager(
            mContext, RecyclerView.VERTICAL, false
        )
        mContainerRecyclerView?.adapter = mContainerAdapter
    }

    private fun observeEvents() {
        viewModel.containerLiveData.observe(context.getLifecycleOwner(), {
            var list: List<CardGroupModel> = it.cardGroups ?: emptyList()
            if (viewModel.isFirstTimeContainerCalled && viewModel.cardStats == PARTIAL_GONE) {
                setCardStatus(VISIBLE)
            } else if (viewModel.cardStats in arrayOf(PARTIAL_GONE, PERMANENT_GONE)) {
                list = list.filter { it.designType != Constants.BIG_DISPLAY_CARD.key }
            }
            viewModel.isFirstTimeContainerCalled = false
            mContainerAdapter.addList(list)
            cardList = list
        })
    }

    private fun setCardStatus(cardStatus:String)
    {
        viewModel.setCardStatus(cardStatus)
    }

    companion object
    {
        const val VISIBLE = "VISIBLE"
        const val PARTIAL_GONE = "PARTIAL_GONE"
        const val PERMANENT_GONE = "PERMANENT_GONE"
    }

}