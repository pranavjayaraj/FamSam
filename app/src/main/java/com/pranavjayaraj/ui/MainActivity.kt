package com.pranavjayaraj.ui

import android.view.LayoutInflater
import com.pranavjayaraj.base.BaseActivityVB
import com.pranavjayaraj.databinding.ActivityMainBinding
import com.pranavjayaraj.ui.viewModels.HomeViewModel
import javax.inject.Inject

class MainActivity: BaseActivityVB<ActivityMainBinding>() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun setup() {
        homeViewModel.getContainer()
    }

    override fun attachBinding(list: MutableList<ActivityMainBinding>, inflater: LayoutInflater) {
        TODO("Not yet implemented")
    }

}