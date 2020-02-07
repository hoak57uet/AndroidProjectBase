package com.kingsofts.projectbase.screen.main

import android.widget.Toast
import com.kingsofts.basemodule.base.impl.BaseActivity
import com.kingsofts.basemodule.data.BaseSharedPreference
import com.kingsofts.basemodule.permission.PermissionRequestCallback
import com.kingsofts.basemodule.screen.splash.ConfigurationData
import com.kingsofts.basemodule.utils.AdsUtils
import com.kingsofts.basemodule.utils.Constant
import com.kingsofts.projectbase.R

class MainActivity : MainView, BaseActivity<MainPresenter>(), PermissionRequestCallback {
    override fun initView() {
        super.permissionGrantedCallback = this
        var permissions = ArrayList<String>()
        permissions.add(android.Manifest.permission.WRITE_CONTACTS)
        checkPermissionAndRequest(permissions)
        //test shared preference
        var data =
            BaseSharedPreference.getObject(Constant.KEY_PREF_CONFIG, ConfigurationData::class.java)
        Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show()
        AdsUtils.bannerAds(this, findViewById(R.id.main_banner_ads), data?.bannerAdsId!!)
    }

    override fun initPresenter(): MainPresenter? {
        return MainPresenterImpl(this)
    }

    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun onPermissionGranted() {
        Toast.makeText(this, "Permission is granted!!!", Toast.LENGTH_SHORT).show()
    }
}