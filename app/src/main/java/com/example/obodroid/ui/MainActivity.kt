package com.example.obodroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.obodroid.Control.Control
import com.example.obodroid.databinding.ActivityMainBinding
import com.example.obodroid.model.Retrofit.response.Coins
import com.example.obodroid.ui.Adapters.CoinAdapter
import com.example.obodroid.ui.dialogs.GraphCoins
import com.example.obodroid.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val adapters = CoinAdapter()
    private var coinsArr = ArrayList<Coins>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initInstance()
    }

    private fun initInstance(){
        setAdapters()
        observeData()
        onClickActivate()
    }

    private fun onClickActivate(){
        binding.showGraph.setOnClickListener {
            val fm = supportFragmentManager
            val dialog = GraphCoins(coinsArr)
            dialog.show(fm,"GraphDialog")
        }
    }

    private fun observeData(){
        viewModel.getCoins()
        viewModel.coinLiveData.observe(this,{
            coinsArr = it.data.coins
            adapters.setList(it.data.coins)
        })
    }

    private fun setAdapters(){
        binding.recyclerCoin.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapters
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Control.screen_width = binding.root.measuredWidth
        Control.screen_height = binding.root.measuredHeight
        println("Width of screen = ${Control.screen_width}")
        println("Height of screen = ${Control.screen_height}")
    }


}