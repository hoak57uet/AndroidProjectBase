package com.kingsofts.basemodule.base.impl

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.kingsofts.basemodule.base.BasePresenter
import com.kingsofts.basemodule.base.BaseView
import com.kingsofts.basemodule.permission.PermissionRequestCallback
import com.kobakei.ratethisapp.RateThisApp
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.kingsofts.basemodule.R
import com.kingsofts.basemodule.dialog.BaseAlertDialog
import com.kingsofts.basemodule.utils.PermissionUtils


abstract class BaseActivity<P> : AppCompatActivity(),
    BaseView where P : BasePresenter {
    val CURRENT_REQUEST_PERMISSION = 1999
    protected var mPresenter: P? = null
    protected var permissionGrantedCallback: PermissionRequestCallback? = null
    override fun onStart() {
        super.onStart()
        mPresenter = initPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initLayout())
        configBasicView()
        initView()
        mPresenter?.loadData()
    }

    private fun configBasicView() {
        val config = RateThisApp.Config(3, 5)
        RateThisApp.init(config)
        // Monitor launch times and interval from installation
        RateThisApp.onCreate(this);
        // If the condition is satisfied, "Rate this app" dialog will be shown
        RateThisApp.showRateDialogIfNeeded(this);
    }

    abstract fun initPresenter(): P?

    abstract fun initLayout(): Int

    override fun showLoading() {
        var loadingBar =
            findViewById<ProgressBar>((resources.getIdentifier("loading_bar", "id", packageName)))
        loadingBar?.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        var loadingBar =
            findViewById<ProgressBar>((resources.getIdentifier("loading_bar", "id", packageName)))
        loadingBar?.visibility = View.VISIBLE
    }

    protected fun checkPermissionAndRequest(requestPermissions: List<String>) {
        var names = PermissionUtils.getNames(requestPermissions)
        var messageErr =
            "$names ${resources.getString(R.string.permission_err)}"
        Dexter.withActivity(this)
            .withPermissions(
                requestPermissions
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (!report?.areAllPermissionsGranted()!!) {
                        BaseAlertDialog.showSimpleAlertDialog(
                            context = this@BaseActivity,
                            message = messageErr,
                            positiveButtonText = "OK"
                        )
                    } else {
                        permissionGrantedCallback?.onPermissionGranted()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    BaseAlertDialog.showSimpleAlertDialog(
                        context = this@BaseActivity,
                        message = messageErr,
                        positiveButtonText = "OK"
                    )
                }

            })
            .check()
    }
}