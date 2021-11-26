package com.example.obodroid.ui.dialogs

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.obodroid.Control.Control
import com.example.obodroid.R
import com.example.obodroid.databinding.DialogGraphCoinsBinding
import com.example.obodroid.model.CoinsEntry
import com.example.obodroid.model.Retrofit.response.Coins
import com.example.obodroid.utils.MyValueFormatter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class GraphCoins(val coins: ArrayList<Coins>): DialogFragment() {
    private lateinit var myContext: Context
    private val binding by lazy { DialogGraphCoinsBinding.inflate(layoutInflater) }
    private var coinEnt = ArrayList<CoinsEntry>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initInstance()

        return binding.root
    }

    private fun initInstance(){
        createLineChart()
        onClickActivate()
    }

    private fun onClickActivate(){
        binding.backBtn.setOnClickListener {
            dismiss()
        }
    }

    private fun createLineChart(){
        binding.lineChart.axisLeft.setDrawGridLines(false)
        val xAxis = binding.lineChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.legend.isEnabled = false
        binding.lineChart.description.isEnabled = false

        binding.lineChart.animateX(1000, Easing.EaseInSine)
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.valueFormatter = CoinAxisFormatter()
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = +90f

        setLineData()
    }

    private fun setLineData(){
        val entries = ArrayList<Entry>()
        coinEnt = getCoinList()
        for (i in coins.indices){
            entries.add(Entry(i.toFloat(), coinEnt[i].price))
        }
        val lineDataSet = LineDataSet(entries, "")
        val data = LineData(lineDataSet)
        binding.lineChart.data = data
        binding.lineChart.invalidate()
    }

    private fun getCoinList(): ArrayList<CoinsEntry> {
        for (i in coins.indices){
            coinEnt.add(CoinsEntry(coins[i].symbol, coins[i].price.toFloat()))
        }

        return coinEnt
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onStart() {
        super.onStart()
        val width = Control.screen_width - (Control.screen_width * 10) /100
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

    inner class CoinAxisFormatter: IndexAxisValueFormatter(){
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index < coinEnt.size) {
                coinEnt[index].symbol
            } else {
                ""
            }
        }
    }
}