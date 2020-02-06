package com.kingsofts.basemodule.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    companion object {
        lateinit var retrofit: Retrofit
        var services: HashMap<String, Any> = HashMap()

        fun <T> initRootService(clazz: Class<T>, url: String) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            var service = retrofit.create<T>(clazz)
            services[clazz.name] = service as Any
        }

        fun <T> getService(clazz: Class<T>): T {
            return services[clazz.name] as T
        }
    }

}