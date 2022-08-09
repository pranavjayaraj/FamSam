package com.pranavjayaraj.utils

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
class GlideDelegate {

    private lateinit var glide: RequestManager

    constructor(fragment: Fragment) {
        glide = Glide.with(fragment)
    }

    constructor(context: Context) {
        glide = Glide.with(context)
    }

    fun loadUrlWithPlaceHolder(
        imageView: AppCompatImageView,
        url: String?,
        placeholder: Int,
        isCache: Boolean = true
    ) {
        glide.load(url ?: "")
            .apply(
                RequestOptions().apply {
                    if (placeholder != -1) {
                        placeholder(placeholder)
                            .skipMemoryCache(true)
                    }
                    if (!isCache) {
                        diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                    }
                })
            .into(imageView)
    }
}