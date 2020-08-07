package com.example.music_recomend_profile.setting

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DateDialogFragment(
    val birthYear: Int?,
    val birthMonth: Int?,
    val birthDay: Int?,
    val callBack: (Int, Int, Int) -> Unit
) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        if (
            birthYear != null && birthMonth != null && birthDay != null
        ) {
            return DatePickerDialog(context!!, this, birthYear, birthMonth, birthDay)
        }

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(context!!, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
        callBack(year, month, day)
    }
}