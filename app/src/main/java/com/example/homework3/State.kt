package com.example.homework3

sealed class State

object Error: State()
object Progress: State()
class Factorial(
    val value: Double
): State()