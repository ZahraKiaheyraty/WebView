package com.example.webview.ui.intro

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.webview.R
import com.example.webview.databinding.FragmentIntroBinding
import com.example.webview.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IntroFragment : Fragment(R.layout.fragment_intro) {

    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.networkObserve.observe(this, Observer {
            when (it) {
                true -> {

                    lifecycleScope.launch {
                        navigation()
                    }
                }
                false -> {
                    binding.apply {

                        poorConnection.isVisible = true
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentIntroBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        viewModel.onSubscribe(this)
        checkConnection()
    }

    private fun checkConnection(){
       binding.poorConnection.setOnClickListener {
           viewModel.onSubscribe(this)
       }
    }

    private suspend fun navigation() {
        delay(3000)
        findNavController().navigate(R.id.action_introFragment_to_mainFragment)
    }



}