package com.pranavjayaraj.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.pranavjayaraj.base.BaseActivityVB
import com.pranavjayaraj.databinding.ActivityMainBinding
import com.pranavjayaraj.widget.containerview.viewModels.ContainerViewModel
import javax.inject.Inject

class HomeActivity: BaseActivityVB<ActivityMainBinding>() {

    @Inject
    lateinit var containerViewModel: ContainerViewModel

    override fun setup() {
        getBinding().containerView.setupData(containerViewModel)
    }

    override fun attachBinding(list: MutableList<ActivityMainBinding>, inflater: LayoutInflater) {
        list.add(ActivityMainBinding.inflate(inflater))
    }

}