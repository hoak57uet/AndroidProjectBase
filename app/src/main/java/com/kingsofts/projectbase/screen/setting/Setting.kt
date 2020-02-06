package com.kingsofts.projectbase.screen.setting;

import com.kingsofts.basemodule.base.impl.BaseActivity
import com.kingsofts.projectbase.R

class SettingActivity : SettingView, BaseActivity<SettingPresenter>() {
    override fun initView() {

    }

    override fun initPresenter(): SettingPresenter? {
        return SettingPresenterImpl(this)
    }

    override fun initLayout(): Int {
        return R.layout.activity_setting
    }
}