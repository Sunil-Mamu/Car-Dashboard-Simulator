package com.example.cardashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.fixedRateTimer

class SpeedViewModel : ViewModel() {
    private val _speed = MutableLiveData<Int>()
    val speed: LiveData<Int> = _speed

    private var timer: Timer? = null

    init {
        startSpeedSimulation()
    }

    private fun startSpeedSimulation() {
        var currentSpeed = 0
        timer = fixedRateTimer("speedTimer", false, 0L, 1000) {
            currentSpeed = (20..120).random()
            _speed.postValue(currentSpeed)
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}
