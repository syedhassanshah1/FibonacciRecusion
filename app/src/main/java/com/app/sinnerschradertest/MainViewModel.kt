package com.app.sinnerschradertest

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var fibonacciItems = flow {
        var index = 0
        try {
            while (true) {
                val result = fibonacciRecursiveTail(index)
//                    val result = fibonacciIterative(index)
//                    val result = fibonacciRecursive(index)

            Thread.sleep(100)
                emit("F($index): $result")
                index++
            }
        }catch (e: IllegalStateException){

        }

    }.flowOn(Dispatchers.IO)

    private tailrec fun fibonacciRecursiveTail(n: Int, a: ULong = 0u, b: ULong = 1u): ULong {
            if (n < 1) {
                return a
            } else {
                if (a > ULong.MAX_VALUE - b) throw IllegalStateException()
               return fibonacciRecursiveTail(n - 1, b, a + b)
            } //since it is a tail recursive function the complexity for it is O(n)
    }
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
}