package com.syarifhidayatullah.myapplication.utils

object Cons {
    const val AUTH_SIGN_IN=1
    const val AUTH_SIGN_UP=2

    object SharePreferenceConfig {
        const val PREFERENCES_TOKEN = "preferences_token"
        const val PREFERENCES_USER = "preferences_user"
    }

    const val DATE_FORMAT_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_FORMAT_SIMPLE = "yyyy-MM-dd"
    const val DATE_FORMAT_DAY_MONTH_YEAR = "dd MMM yyyy"
    const val DATE_FORMAT_DAY_MONTH = "dd MMM"
    const val DATE_FORMAT_MONTH = "MM"
    const val DATE_FORMAT_DAY_NUMBER = "dd"
    const val DATE_FORMAT_DAY = "E"
    const val DATE_FORMAT_CHECKIN = "MMM dd , HH.mm"
    const val DATE_FORMAT_CHECKIN_WITHOUT_TIME = "E, dd MMM"
    const val DATE_FORMAT_DAY_DATE_MONTH_YEAR = "E, dd MMM yyyy"
    const val DATE_MONTH_YEAR = "MMM yyyy"
    const val DATE_HOUR_MINUTE = "HH.mm"
    const val DATE_HOUR_MINUTE_TIME = "HH:mm:ss"
    const val TIME_24_FORMAT = "kk:mm:ss"
}