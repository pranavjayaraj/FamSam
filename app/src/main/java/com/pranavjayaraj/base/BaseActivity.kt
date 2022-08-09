package com.pranavjayaraj.base

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.pranavjayaraj.common.ViewModelFactory
import com.pranavjayaraj.data.checkAndDispose
import com.pranavjayaraj.utils.GlideDelegate
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<VM: ViewModel,Binding: ViewBinding>: DaggerAppCompatActivity(){

    private lateinit var viewModel: VM

    lateinit var view : Binding

    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val glideUtil : GlideDelegate by lazy {
        GlideDelegate(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val statusBarColor = getStatusBarColor()
        if (statusBarColor != -1 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = statusBarColor
        }
        view = getBindings()
        compositeDisposable = CompositeDisposable()
        viewModel = getViewModelInstance()
        val view = view.root
        setContentView(view)
        onViewCreated()
    }

    open fun getStatusBarColor(): Int{
        return -1
    }


    open fun handleErrorMsg(msg: String): Boolean{
        return false
    }

    abstract fun getViewModelInstance(): VM


    abstract fun getBindings():Binding


    open fun initializeAdapter()
    {

    }

    open fun initializeObservers()
    {

    }

    open fun initializeClickListeners()
    {

    }

    open fun onViewCreated()
    {
        initializeAdapter()
        initializeObservers()
        initializeClickListeners()
    }

    override fun onDestroy() {
        compositeDisposable.checkAndDispose()
        super.onDestroy()
    }
}