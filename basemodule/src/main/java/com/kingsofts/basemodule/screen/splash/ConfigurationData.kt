package com.kingsofts.basemodule.screen.splash

import com.google.gson.annotations.SerializedName

/**
 * Application config is loaded from server
 */
class ConfigurationData {
    @SerializedName("is_active")
    var active: Boolean? = null
    @SerializedName("ads_banner_unit_ids")
    val bannerAdsId: String? = null
    @SerializedName("ads_native_unit_ids")
    val nativeAdsId: String? = null
    @SerializedName("ads_video_unit_ids")
    val videoAdsId: String? = null
    @SerializedName("ads_video_step")
    val videoAdsStep: Int = 10
    @SerializedName("native_ads_page_step")
    val nativeAdsStep: Int = 10
    @SerializedName("maintenance_message")
    val maintenance: String? = null
    @SerializedName("link_maintenance")
    val linkMaintenance: String? = null
    @SerializedName("ads_app_id")
    val appAdsId: String? = null
    @SerializedName("show_ads_banner")
    val showBannerAds: Boolean = false
    @SerializedName("show_ads_native")
    val showNativeAds: Boolean = false
    @SerializedName("show_ads_video")
    val showVideoAds: Boolean = false
}