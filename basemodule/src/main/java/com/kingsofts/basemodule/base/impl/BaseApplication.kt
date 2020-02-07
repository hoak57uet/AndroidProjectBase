package com.kingsofts.basemodule.base.impl

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.kingsofts.basemodule.BuildConfig
import com.kingsofts.basemodule.network.NetworkService
import com.kingsofts.basemodule.network.configservice.ConfigService
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType
import com.nostra13.universalimageloader.utils.StorageUtils
import com.onesignal.OneSignal

abstract class BaseApplication : Application() {
    companion object {
        lateinit var Instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        Instance = this
        initConfigService()
        initImageLoader()
        initOneSignal()
        initAds()
    }

    private fun initAds() {
        MobileAds.initialize(this)
    }

    private fun initOneSignal() {
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init();
    }

    private fun initConfigService() {
        if (!BuildConfig.APP_CONFIG_URL.isNullOrBlank())
            NetworkService.initRootService(
                ConfigService::class.java,
                BuildConfig.APP_CONFIG_URL
            )
    }

    private fun initImageLoader() {
        var cacheDir = StorageUtils.getCacheDirectory(this)
        var config = ImageLoaderConfiguration.Builder(this)
//            .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
            .diskCacheExtraOptions(1080, 1920, null)
            .threadPoolSize(4) // default
            .threadPriority(Thread.NORM_PRIORITY - 2) // default
            .tasksProcessingOrder(QueueProcessingType.FIFO) // default
            .denyCacheImageMultipleSizesInMemory()
            .memoryCache(LruMemoryCache(10 * 1024 * 1024))
            .memoryCacheSize(10 * 1024 * 1024)
            .memoryCacheSizePercentage(13) // default
            .diskCache(UnlimitedDiskCache(cacheDir)) // default
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileCount(100)
            .diskCacheFileNameGenerator(HashCodeFileNameGenerator()) // default
            .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
            .writeDebugLogs()
            .build()
        ImageLoader.getInstance().init(config)
    }
}