package com.pranavjayaraj.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.content.res.Resources
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
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