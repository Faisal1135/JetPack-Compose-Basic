package com.example.composebasic.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Telephony


@SuppressLint("Recycle")
fun getSmsWithContext(context: Context): List<SmsData> {
    val smsList = mutableListOf<SmsData>()
    context.contentResolver.query(Telephony.Sms.CONTENT_URI, null, null, null, null)
        ?.let { cursor ->

            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val address =
                        cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                    val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))
                    smsList.add(SmsData(address, body))

                }
            }
        }
    return smsList

}