package com.example.patient.utils.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.example.patient.BuildConfig
import com.example.patient.R

object Utils {
    fun setUpPermission(context: Context, permissionClicked: () -> Unit = { }) {
        InfoAlert.showConfirmAlert(context, context.getString(R.string.order_cour_need_permission), context.getString(R.string.map_need_permission), context.getString(R.string.settings), context.getString(R.string.cancel)) {
            permissionClicked.invoke()
            context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)))
        }
    }
}