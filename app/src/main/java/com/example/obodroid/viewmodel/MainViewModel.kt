package com.example.obodroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obodroid.model.API
import com.example.obodroid.model.Retrofit.response.CoinsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    var coinLiveData = MutableLiveData<CoinsResponse>()

    fun getCoins(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = API.retrofit.getCoins()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        coinLiveData.postValue(body)
                        //println("Body id $body")
                    }
                }
            }
        }
    }
}