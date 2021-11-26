package com.example.obodroid.utils

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

class MyValueFormatter : ValueFormatter() {
    private lateinit var mFormat: DecimalFormat

    override fun getFormattedValue(value: Float, axis: AxisBase): String {
        mFormat = DecimalFormat("###,###,##0")
        return mFormat.format(value).toString() + " คน"
    }

}