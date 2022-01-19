package com.example.domain

import java.text.SimpleDateFormat
import java.util.*

fun convertDateString(dateString: String): String {
    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString)
    println(date)
    val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    return dateFormat.format(date)
}