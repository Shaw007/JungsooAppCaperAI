package com.srmstudios.jungsoomarket.util

import android.widget.ImageView
import androidx.core.net.toUri
import coil.load
import com.srmstudios.jungsoomarket.R

fun ImageView.loadHttpsUrl(httpsUrl: String){
    load(httpsUrl.toUri().buildUpon().scheme("https").build()){
        placeholder(R.drawable.loading_img)
        error(R.drawable.ic_broken_image)
    }
}