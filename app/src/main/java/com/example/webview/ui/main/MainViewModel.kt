package com.example.webview.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.example.webview.ConnectivityLiveData
import com.example.webview.util.CheckPing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var network: ConnectivityLiveData,val check:Boolean) :
    ViewModel() {

    var checkNewWork = MutableLiveData<Boolean>(false)
    var networkObserve: LiveData<Boolean> = checkNewWork




    init {

        if(check){
            when(isConnected()){
                true -> checkNewWork.value = true
                false-> checkNewWork.value = false
            }
        } else{
               checkNewWork.value = false
        }


    }

    fun onSubscribe(context: LifecycleOwner) {
        viewModelScope.launch {
            network.observe(context, Observer {
                when (it) {
                    false -> {
                        checkNewWork.value = false
                    }
                    true -> {
                        when(isConnected()){
                            true -> {
                                checkNewWork.value = true

                            }
                            false-> {
                                checkNewWork.value = false

                            }
                        }

                    }


                }

            })
        }


    }

    @Throws(InterruptedException::class, IOException::class)
    fun isConnected(): Boolean {
        val command = "ping -c 1 google.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }
}