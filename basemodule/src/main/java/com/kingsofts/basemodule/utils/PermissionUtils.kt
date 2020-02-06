package com.kingsofts.basemodule.utils

class PermissionUtils {
    companion object {
        fun getNames(requestPermissions: List<String>): String {
            return requestPermissions.map { permission -> permission.split(".").last() }
                .joinToString(",")
        }
    }
}