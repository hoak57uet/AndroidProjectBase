package com.kingsofts.basemodule.screen.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.kingsofts.basemodule.R
import com.kingsofts.basemodule.network.NetworkService
import com.kingsofts.basemodule.network.configservice.ConfigService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getConfigurationData()
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
                }

                override fun onResponse(
                    call: Call<ConfigurationData>,
                    response: Response<ConfigurationData>
                ) {
                    startActivity(Intent(this@BaseSplashActivity, initMainScreen()))
                    this@BaseSplashActivity.finish()
                }

            })
    }

    abstract fun initMainScreen(): Class<*>?

}