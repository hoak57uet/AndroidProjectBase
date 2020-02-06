package com.kingsofts.basemodule.utils

import android.graphics.Bitmap
import android.os.Handler
import com.kingsofts.basemodule.R
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer

class ImageOptionUtils {
    companion object {
        fun default(): DisplayImageOptions {
            return DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_stub) // resource or drawable
                .showImageForEmptyUri(R.drawable.no_image_available) // resource or drawable
                .showImageOnFail(R.drawable.no_image_available) // resource or drawable
                .resetViewBeforeLoading(true)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(SimpleBitmapDisplayer()) // default
                .handler(Handler()) // default
                .build()
        }
    }
}