package com.ntech.nmovie.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ntech.nmovie.util.Constants

object ImageBindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {

        url?.let {
            if(url.isNotEmpty()) {
                Glide.with(imageView.context)
                    .load(Constants.IMAGE_BASE_URL + Constants.IMAGE_W342 + url)
                    .into(imageView)

            }
        }


    }

}