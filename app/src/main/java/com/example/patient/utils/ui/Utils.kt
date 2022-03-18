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
    fun getTypes(): List<Pair<Int, String>> =
        listOf(
            Pair(1, "Дом здоровья"), Pair(1, "Центр здоровья"), Pair(1, "Сельскый центр здоровья"),
            Pair(1, "Ройонный центр здоровья"), Pair(1, "Сельская участковая больница"),
            Pair(1, "Сельская номерная больница"), Pair(1, "Центральная районная больница"),
            Pair(1, "Роддом третьего уровня/перинатальный центр")
        )
}