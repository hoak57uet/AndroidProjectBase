package com.kingsofts.basemodule.screen.splash

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import com.kingsofts.basemodule.BuildConfig
import com.kingsofts.basemodule.R
import com.kingsofts.basemodule.data.BaseSharedPreference
import com.kingsofts.basemodule.network.NetworkService
import com.kingsofts.basemodule.network.configservice.ConfigService
import com.kingsofts.basemodule.utils.Constant
import com.kingsofts.basemodule.utils.ImageOptionUtils
import com.nostra13.universalimageloader.core.ImageLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
        getConfigurationData()
    }

    private fun initView() {
        ImageLoader.getInstance()
            .displayImage(
                BuildConfig.APP_SPLASH_LOGO,
                findViewById<ImageView>(R.id.splash_logo_img),
                ImageOptionUtils.default()
            )
    }

    private fun getConfigurationData() {
        NetworkService.getService(ConfigService::class.java).getApplicationConfig("nail")
            .enqueue(object : Callback<ConfigurationData> {
                override fun onFailure(call: Call<ConfigurationData>, t: Throwable) {
                    Toast.makeText(
                        this@BaseSplashActivity,
                        "Failed to get config this app!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@BaseSplashActivity, initMainScreen()))
                    this@BaseSplashActivity.finish()
                }

                override fun onResponse(
                    call: Call<ConfigurationData>,
                    response: Response<ConfigurationData>
                ) {
                    if (response.body() != null) {
                        //save config to sharedPreference
                        BaseSharedPreference.putObject(Constant.KEY_PREF_CONFIG, response.body())
                    }
                    startActivity(Intent(this@BaseSplashActivity, initMainScreen()))
                    this@BaseSplashActivity.finish()
                }

            })
    }


    abstract fun initMainScreen(): Class<*>?

}