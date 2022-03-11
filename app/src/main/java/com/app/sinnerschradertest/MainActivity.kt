package com.app.sinnerschradertest

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.sinnerschradertest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val fibonacciItems = mutableListOf<String>()
    private val fibonacciItemAdapter by lazy {
        FibonacciItemAdapter(fibonacciItems)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel.initFibonacciSeries()
        binding.rvFibonacciSeries.adapter = fibonacciItemAdapter
        mainViewModel.fibonacciResult.observe(this, {
            fibonacciItems.add(it)
            fibonacciItemAdapter.notifyItemInserted(fibonacciItems.size - 1)
        })
    }
}