package com.appiness

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.Calendar

@BindingAdapter("millisToDate")
fun TextView.millisToDate(millis:Long) {
    val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = millis
    this.text =formatter.format(calendar.time)
}