package com.kingsofts.projectbase.screen.main

import android.widget.Toast
import com.kingsofts.basemodule.base.impl.BaseActivity
import com.kingsofts.basemodule.permission.PermissionRequestCallback
import com.kingsofts.projectbase.R

class MainActivity : MainView, BaseActivity<MainPresenter>(), PermissionRequestCallback {
    override fun initView() {
        super.permissionGrantedCallback = this
        var permissions = ArrayList<String>()
        permissions.add(android.Manifest.permission.WRITE_CONTACTS)
        checkPermissionAndRequest(permissions)
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