package com.example.obodroid.viewmodel

import androidx.lifecycle.*
import com.example.obodroid.model.API
import com.example.obodroid.model.Retrofit.response.CoinsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
                    }
                }
            }
        }
    }
}