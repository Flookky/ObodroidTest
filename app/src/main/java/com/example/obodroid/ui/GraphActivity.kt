package com.example.obodroid.ui

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.obodroid.R
import com.example.obodroid.databinding.ActivityGraphBinding
import com.example.obodroid.databinding.ActivityMainBinding
import com.example.obodroid.model.Retrofit.response.Coins
import com.example.obodroid.utils.MyValueFormatter
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class GraphActivity(val coins: ArrayList<Coins>): BaseActivity() {
    private val binding by lazy { ActivityGraphBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initInstance()
    }

    private fun initInstance(){

    }

    private fun createLineChart(){

    }

    private fun setLineData(){

    }

    private fun createBarChart(){
        binding.horizonChart.animateXY(0,500)
        binding.horizonChart.setDrawBarShadow(false)
//        val description = Description()
//        description.text = ""
        binding.horizonChart.description = null
        binding.horizonChart.legend.isEnabled = false
        binding.horizonChart.setPinchZoom(false)
        binding.horizonChart.setDrawValueAboveBar(true)
        binding.horizonChart.setFitBars(true)
        binding.horizonChart.setViewPortOffsets(200f, 0f, 150f, 0f)

        val xAxis = binding.horizonChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.isEnabled = true
        xAxis.setDrawAxisLine(true)
        xAxis.axisLineWidth = 2f
        xAxis.axisLineColor = Color.BLACK
        xAxis.setLabelCount(coins.size, false)

//        val values = arrayOf("ปรับปรุง", "พอใช้", "ดี", "ดีมาก", "ดีเยี่ยม")
        val values = ArrayList<String>()
        values.clear()
        for (i in coins.indices){
            values.add(coins[i].symbol)
        }
        //val tf = ResourcesCompat.getFont(myContext, R.font.quark_bold)
        xAxis.valueFormatter = IndexAxisValueFormatter(values)
        //xAxis.typeface = tf
        xAxis.textSize = 13f

        val yLeft = binding.horizonChart.axisLeft
//        yLeft.axisMaximum = 100f
        yLeft.axisMinimum = 0f
        yLeft.isEnabled = false

        val yRight = binding.horizonChart.axisRight
        yRight.setDrawAxisLine(true)
        yRight.setDrawGridLines(false)
        yRight.setDrawLabels(false)
        yRight.axisMinimum = 0f
        yRight.axisLineWidth = 2f
        yRight.axisLineColor = Color.BLACK
        yRight.isEnabled = true
//        yRight.setValueFormatter(MyValueFormatter())

        //yRight.typeface = tf
        yRight.textSize = 13f

        setGraphData()
    }

    private fun setGraphData(){
        //Add a list of bar entries
        val entries = ArrayList<BarEntry?>()
        coins.sortByDescending { it.name }
        for (i in coins.indices){
            entries.add(BarEntry(i.toFloat(), coins[i].price.toFloat()))
        }

        val barDataSet = BarDataSet(entries, "Bar Data Set")
        barDataSet.setColors(
            ContextCompat.getColor(binding.horizonChart.context, R.color.purple_700)
        )

        val data = BarData(barDataSet)
        data.barWidth = 0.5f
        data.setValueTextSize(10f)
        data.setValueFormatter(MyValueFormatter())
        data.setValueTextSize(13f)

        binding.horizonChart.data = data
        binding.horizonChart.invalidate()

    }
}