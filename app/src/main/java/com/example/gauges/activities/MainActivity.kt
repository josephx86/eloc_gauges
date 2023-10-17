package com.example.gauges.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gauges.R
import com.example.gauges.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNumberPickers()
        binding.gpsNumberPicker.value = binding.gpsGauge.getValue().toInt()
        binding.batteryNumberPicker.value = binding.batteryGauge.getValue().toInt()
    }

    private fun setupNumberPickers() {
        val min = 0
        val max = 100
        val displayValues: MutableList<String> = mutableListOf()
        for (i in min..max) {
            displayValues.add(i.toString())
        }

        binding.storageNumberPicker.wrapSelectorWheel = false
        binding.storageNumberPicker.minValue = 0
        binding.storageNumberPicker.maxValue = 100
        binding.storageNumberPicker.displayedValues = displayValues.toTypedArray()
        binding.storageNumberPicker.setOnValueChangedListener { _, _, value ->
            binding.storageGauge.updateValue(value.toDouble())
            binding.storageStatus.text = getString(R.string.free_space, value)
        }

        binding.batteryNumberPicker.wrapSelectorWheel = false
        binding.batteryNumberPicker.minValue = 0
        binding.batteryNumberPicker.maxValue = 100
        binding.batteryNumberPicker.displayedValues = displayValues.toTypedArray()
        binding.batteryNumberPicker.setOnValueChangedListener { _, _, value ->
            binding.batteryGauge.updateValue(value.toDouble())
            binding.batteryStatus.text = getString(R.string.battery_level, value)
        }

        binding.gpsNumberPicker.wrapSelectorWheel = false
        binding.gpsNumberPicker.minValue = 0
        binding.gpsNumberPicker.maxValue = 100
        binding.gpsNumberPicker.displayedValues = displayValues.toTypedArray()
        binding.gpsNumberPicker.setOnValueChangedListener { _, _, value ->
            binding.gpsGauge.updateValue(value.toDouble())
            val res = if (value >= 100) R.string.gps_long_distance else R.string.gps_distance
            binding.gpsStatus.text = getString(res, value)
        }
    }
}