package com.example.pirates_challenge.common

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest

object ImageUtils {

    fun createIconUri(icon: String): String {
        return "http://openweathermap.org/img/wn/$icon@2x.png"
    }

    fun SimpleDraweeView.setUrl(url: String) {
        val controller = Fresco.newDraweeControllerBuilder()
            .setImageRequest(ImageRequest.fromUri(url))
            .setOldController(this.controller).build()
        this.controller = controller
    }
}