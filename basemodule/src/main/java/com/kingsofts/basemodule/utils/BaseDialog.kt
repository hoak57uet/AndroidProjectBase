package com.kingsofts.basemodule.utils

import android.app.AlertDialog
import android.content.Context

class BaseDialog {
    companion object {
        var dialog: AlertDialog? = null
        private fun instance(context: Context?): AlertDialog {
            if (dialog == null) {
                dialog = AlertDialog.Builder(context)
                    .setMessage("Loading")
                    .create()

            }
            return dialog!!
        }

        fun showLoadingDialog(context: Context) {
            instance(context).show()
        }

        fun dismiss() {
            instance(null).dismiss()
        }
    }
}