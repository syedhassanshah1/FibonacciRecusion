package com.app.sinnerschradertest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val fibonacciResult = MutableLiveData<String>()
    private tailrec fun fibonacciRecursiveTail(n: ULong, a: ULong = 0u, b: ULong = 1u): ULong =
            if (n < 1u) a else fibonacciRecursiveTail(n - 1u, b, a + b) //since it is a tail recursive function the complexity for it is O(n)

    private fun fibonacciRecursive(n: ULong): ULong = if (n < 2u) n else fibonacciRecursive(n - 1u) + fibonacciRecursive(n - 2u) // As this is a recursive function the complexity is O(n^2)

    // As this is a iterative function the complexity is O(n)
    private fun fibonacciIterative(n: ULong): ULong {
        var index = 2u
        if (n < index) return n
        var indexMinusOne: ULong = 1u
        var indexMinusTwo: ULong = 0u
        var result = indexMinusOne
        while (index <= n) {
            result = indexMinusOne + indexMinusTwo
            indexMinusTwo = indexMinusOne
            indexMinusOne = result
            index++
        }

        return result
    }

    fun initFibonacciSeries() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var index: ULong = 0u
                while (index <= ULong.MAX_VALUE) {
                    val result = fibonacciRecursiveTail(index)
//                    val result = fibonacciIterative(index)
//                    val result = fibonacciRecursive(index)
                    withContext(Dispatchers.Main) { // returning the result from the Dispatchers.Main so that the sequence is persisted as compared to send it from background thread using PostValue
                        fibonacciResult.value = ("F($index): $result")
                    }
                    Thread.sleep(100)
                    index++
                }
            }
        }
    }
}