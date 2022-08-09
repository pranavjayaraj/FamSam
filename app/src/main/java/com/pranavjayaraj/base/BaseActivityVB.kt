package com.pranavjayaraj.base

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.pranavjayaraj.base.VBHelpersImpl.Companion.ERROR_ADD_BINDING_TO_LIST

abstract class BaseActivityVB<VB_CHILD : ViewBinding>(
    private val helperImpl: VBHelpersImpl<VB_CHILD> = VBHelpersImpl()
) : BaseActivity(), VBHelpers<VB_CHILD> by helperImpl {
    override val contentView = VBHelpersImpl.RES_NO_LAYOUT

    /**
     *
     * As you can see, we are using effectively final variables to get access to children's
     * Binding Class instance. This is done to prevent attachBinding() from returning any value,
     * thereby allowing the child classes to access the binding instance from only the parent's
     * helper functions ([withBinding] , [getBinding] , etc)
     * */
    override fun getExternallyInflatedView(inflater: LayoutInflater): View {
        val bindingList = mutableListOf<VB_CHILD>()
        attachBinding(bindingList, inflater)
        val binding: VB_CHILD = bindingList.getOrNull(0) ?: error(ERROR_ADD_BINDING_TO_LIST)
        helperImpl.updateBinding(binding)
        return helperImpl.getBinding().root
    }

    /**
     * must be implemented by a class that wishes to use view binding. such class must add their
     * viewBinding class instance to the list provided in the param . eg
     * `tempList.add(ActivityMainBinding.inflate(inflater))`
     * */
    abstract fun attachBinding(
        list: MutableList<VB_CHILD>,
        inflater: LayoutInflater
    )

    override fun onDestroy() {
        super.onDestroy()
        helperImpl.destroyBinding()
    }
}