package com.kingsofts.projectbase.screen.splash

import android.os.Bundle
import com.kingsofts.basemodule.screen.splash.BaseSplashActivity
import com.kingsofts.projectbase.screen.main.MainActivity

class SplashActivity : BaseSplashActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun initMainScreen(): Class<*>? {
        return MainActivity::class.java
    }
}