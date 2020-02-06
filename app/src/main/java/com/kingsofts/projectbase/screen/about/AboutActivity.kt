package com.kingsofts.projectbase.screen.about

import com.kingsofts.basemodule.base.impl.BaseActivity
import com.kingsofts.projectbase.R

class AboutActivity : AboutView, BaseActivity<AboutPresenter>() {
    /**
     * init view before loading data
     */
    override fun initView() {

    }

    override fun initPresenter(): AboutPresenter? {
        return AboutPresenterImpl(this)
    }

    override fun initLayout(): Int {
        return R.layout.activity_about
    }
}