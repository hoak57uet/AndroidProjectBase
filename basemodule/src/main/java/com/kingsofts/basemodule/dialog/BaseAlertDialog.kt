package com.kingsofts.basemodule.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class BaseAlertDialog {
    companion object {
        fun showSimpleAlertDialog(
            context: Context,
            title: String? = null,
            message: String?,
            positiveButtonText: String?
        ) {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText,
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                .show()
        }
    }
}