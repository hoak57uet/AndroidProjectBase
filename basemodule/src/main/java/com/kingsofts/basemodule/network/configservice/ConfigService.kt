package com.kingsofts.basemodule.network.configservice

import com.kingsofts.basemodule.screen.splash.ConfigurationData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfigService {
    @GET("configurations")
    fun getApplicationConfig(@Query("app") appName: String): Call<ConfigurationData>
}