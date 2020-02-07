package com.kingsofts.basemodule.utils

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.NativeExpressAdView
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd


class AdsUtils {
    companion object {
        fun bannerAds(context: Context, adMobView: RelativeLayout, adsUnitId: String) {
            try {

                var mAdView = PublisherAdView(context)
                mAdView.setAdSizes(AdSize.BANNER)
                mAdView.adUnitId = adsUnitId
                adMobView.addView(mAdView)
                val adRequest = PublisherAdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build()
                mAdView.loadAd(adRequest)
                mAdView.adListener = object : AdListener() {
                    override fun onAdFailedToLoad(i: Int) {
                        super.onAdFailedToLoad(i)
                        adMobView.visibility = View.GONE

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun nativeExpress(context: Context, adMobViewNative: RelativeLayout, adUnitId: String) {
            try {
                var nativeExpressAdView = NativeExpressAdView(context)
                nativeExpressAdView.adSize = AdSize.FLUID
                nativeExpressAdView.adUnitId = adUnitId
                adMobViewNative.addView(nativeExpressAdView)
                val adRequest = AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build()
                nativeExpressAdView.loadAd(
                    adRequest
                )
                nativeExpressAdView.adListener = object : AdListener() {
                    override fun onAdFailedToLoad(i: Int) {
                        super.onAdFailedToLoad(i)
                        adMobViewNative.visibility = View.GONE

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
//            val adLoader = AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
//                .forUnifiedNativeAd { nativeAd: UnifiedNativeAd ->
//                    // Show the ad.
//                    nativeAd.destroy()
//                    val adView = UnifiedNativeAdView(context)
//                    adView.setNativeAd(nativeAd)
//                    adMobViewNative.removeAllViews()
//                    adMobViewNative.addView(adView)
//                }
//                .withAdListener(object : AdListener() {
//                    override fun onAdFailedToLoad(errorCode: Int) {
//                        super.onAdFailedToLoad(errorCode)
//                        adMobViewNative.visibility = View.GONE
//                    }
//                })
//                .withNativeAdOptions(
//                    NativeAdOptions.Builder()
//                        // Methods in the NativeAdOptions.Builder class can be
//                        // used here to specify individual options settings.
//                        .build()
//                )
//                .build()
//
//            adLoader.loadAds(
//                AdRequest.Builder()
//                    .build(), 3
//            )


        }


        fun requestInterstitial(context: Context, adUnitId: String?) {
            if (adUnitId == null)
                return
            val mPublisherInterstitialAdPlay = PublisherInterstitialAd(context)
            mPublisherInterstitialAdPlay.adUnitId = adUnitId

            mPublisherInterstitialAdPlay.loadAd(
                PublisherAdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build()
            )
            mPublisherInterstitialAdPlay.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    mPublisherInterstitialAdPlay.show()
                }
            }


        }
    }
}