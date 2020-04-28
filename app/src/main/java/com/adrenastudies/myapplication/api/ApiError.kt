package com.adrenastudies.myapplication.api

import android.app.Activity
import com.adrenastudies.myapplication.R


class ApiError {

    fun getErrorMessage(act: Activity, errorCode: String): String {

        when (errorCode) {
            "SYS-API" -> return act.applicationContext.resources.getString(R.string.error_api_generic)
            "GE-001" -> return act.applicationContext.resources.getString(R.string.error_api_generic_2)
        }

        return act.applicationContext.resources.getString(R.string.error_api_generic)
    }
}
