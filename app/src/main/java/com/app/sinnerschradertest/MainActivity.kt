package com.app.sinnerschradertest

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.app.sinnerschradertest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

        binding.rvFibonacciSeries.adapter = fibonacciItemAdapter
        this.lifecycleScope.launchWhenStarted {
            mainViewModel.fibonacciItems.onEach {
                fibonacciItems.add(it)
                fibonacciItemAdapter.notifyItemInserted(fibonacciItems.size - 1)
            }.launchIn(this)
        }
    }
}