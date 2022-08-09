package com.pranavjayaraj.utils

import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import android.view.View
import android.content.res.Resources
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> ViewModelProvider.Factory.getViewModel(activity: FragmentActivity): T {
    return ViewModelProvider(activity, this)[T::class.java]
}

inline fun <reified T : ViewModel> ViewModelProvider.Factory.getViewModel(fragment: Fragment): T {
    return ViewModelProvider(fragment, this)[T::class.java]
}

fun ViewGroup.inflate(resId: Int): View = LayoutInflater.from(this.context)
    .inflate(resId, this, false)


fun Context.screenWidth(): Int {
    return resources.displayMetrics.widthPixels
}
fun Int.dpToPx(): Float {
    return (this * Resources.getSystem().displayMetrics.scaledDensity)
}

fun <T> List<T>?.isValidList(position: Int = -1): Boolean {
    if (this.isNullOrEmpty() || position == -1)
        return false

    if (size <= position) return false

    return true
}

fun Context.getLifecycleOwner(): LifecycleOwner {
    return try {
        this as LifecycleOwner
    } catch (exception: ClassCastException) {
        (this as ContextWrapper).baseContext as LifecycleOwner
    }
}

fun Context.dpToPixel(dp: Int): Int =
    (dp * applicationContext.resources.displayMetrics.density).toInt()