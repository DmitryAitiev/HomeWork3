package com.example.homework3

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val setOfNumbers = mutableSetOf<Pair<Double, Double>>()

    fun calculate(value1: String?, value2: String?) {
        _state.value = Progress
        if (value1.isNullOrBlank() || value2.isNullOrBlank()) {
            _state.value = Error
            return
        }
        viewModelScope.launch {
            val number1 = value1.toDouble()
            val number2 = value2.toDouble()
            val result = decision(number1, number2)
            setOfNumbers.add(Pair(number1, number2))
            _state.value = Factorial(result)
        }
    }

    private suspend fun decision(a: Double, b: Double): Double {
        return if (setOfNumbers.contains(Pair(a, b))) {
            sqrt(((a.pow(b)) * b) / 2)
        } else {
            delay(4000)
            sqrt(((a.pow(b)) * b) / 2)
        }
    }
}