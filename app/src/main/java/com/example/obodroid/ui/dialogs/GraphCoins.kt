package com.example.obodroid.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
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
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

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
        binding.lineChart.legend.isEnabled = true
        binding.lineChart.description.isEnabled = false
        binding.lineChart.setScaleMinima(6f,2f)
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
        val lineDataSet = LineDataSet(entries, "มูลค่าของเหรียญ หน่วยบาท")
        val data = LineData(lineDataSet)
        data.setValueTextSize(8F)
        data.setValueFormatter(PriceValueFormatter())
        lineDataSet.lineWidth = 4f
        lineDataSet.valueTextSize = 8f
        lineDataSet.color = ContextCompat.getColor(myContext, R.color.navyBlue)
        lineDataSet.circleHoleColor = ContextCompat.getColor(myContext, R.color.white)
        lineDataSet.setCircleColor(ContextCompat.getColor(myContext, R.color.toolbar))
        lineDataSet.circleSize = 6f
        binding.lineChart.data = data
        binding.lineChart.invalidate()
    }

    private fun getCoinList(): ArrayList<CoinsEntry> {
        for (i in coins.indices){
            coinEnt.add(CoinsEntry(coins[i].symbol, coins[i].price.toFloat()))
        }

        return coinEnt
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyle(STYLE_NO_INPUT, android.R.style.Theme)
        dialog?.window?.decorView?.systemUiVisibility
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog?.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.decorView?.systemUiVisibility = Control.flags
        return super.onCreateDialog(savedInstanceState)
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

    override fun onResume() {
        super.onResume()
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

    inner class PriceValueFormatter: ValueFormatter() {
        private lateinit var mFormat: DecimalFormat

        override fun getFormattedValue(value: Float): String {
            mFormat = DecimalFormat("0.00")
            return mFormat.format(value).toString() + " ฿"
        }
    }
}