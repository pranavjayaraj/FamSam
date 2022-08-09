package com.pranavjayaraj.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.pranavjayaraj.common.ViewModelFactory
import com.pranavjayaraj.data.checkAndDispose
import com.pranavjayaraj.utils.GlideDelegate
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract val contentView: Int
        @LayoutRes
        get

    protected var viewBindingEnabled = false

    val tag: String by lazy { this::class.java.simpleName }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
        attachLayout(layoutInflater)
        setup()
        initClickListeners()
    }

    abstract fun setup()

    open fun initClickListeners()
    {

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    private fun attachLayout(inflater: LayoutInflater) {
        val bindingView: View? = getExternallyInflatedView(inflater)
        when {
            bindingView != null -> setContentView(bindingView)
            contentView != VBHelpersImpl.RES_NO_LAYOUT -> setContentView(contentView)
            else -> error(VBHelpersImpl.ERROR_NO_INFLATED_VIEW)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        var newContext = newBase
        super.attachBaseContext(newContext)
    }

    /**
     * This function is used to provide a view that is already inflated with a layout to Activity
     * call of `setContentView()` . There is no need to override this function, if you are directly
     * extending [BaseActivity] , like `class MyActivity:BaseActivity` .
     *
     *  This function is useful where the class wishes to provide an inflated view manually to the
     *  BaseActivity, such as [BaseActivityVB] . If you wish to create
     *  a new wrapper view binding class, the override this function and pass the
     *  bindedView  (via bindingInstance.root)  here
     * */
    open fun getExternallyInflatedView(inflater: LayoutInflater): View? = null
}