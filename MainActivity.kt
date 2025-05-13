package com.example.cardashboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.cardashboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val speedViewModel: SpeedViewModel by viewModels()

    private val destinations = listOf("Home", "Office", "Gym", "Supermarket")
    private var currentSong = 0
    private val songs = listOf("Drive Safe", "Fuel Up", "AutoTune")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Speedometer updates
        speedViewModel.speed.observe(this, Observer {
            binding.speedText.text = "$it km/h"
        })

        // Theme toggle
        binding.toggleTheme.setOnClickListener {
            val isNight = binding.root.context.resources.configuration.uiMode.toString().contains("NIGHT")
            setTheme(if (isNight) R.style.Theme_CarDashboard_Light else R.style.Theme_CarDashboard_Dark)
            recreate()
        }

        // Destination
        binding.destinationText.text = "Destination: ${destinations.random()}"

        // Music controls
        updateSong()
        binding.btnPlay.setOnClickListener { binding.musicStatus.text = "Playing" }
        binding.btnPause.setOnClickListener { binding.musicStatus.text = "Paused" }
        binding.btnNext.setOnClickListener {
            currentSong = (currentSong + 1) % songs.size
            updateSong()
        }
    }

    private fun updateSong() {
        binding.songTitle.text = "Track: ${songs[currentSong]}"
        binding.musicStatus.text = "Ready"
    }
}
