package com.example.homework3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.homework3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observeViewModel()
        binding.buttonResult.setOnClickListener {
            viewModel.calculate(
                binding.editTextNumber1.text.toString(),
                binding.editTextNumber2.text.toString()
            )
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) {
            binding.progressBarLoading.visibility = View.GONE
            when(it) {
                is Error -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Введите число",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Progress -> {
                    binding.progressBarLoading.visibility = View.VISIBLE
                }
                is Factorial -> {
                    binding.textViewNumber.text = it.value.toString()
                    Toast.makeText(
                        this@MainActivity,
                        "Готово",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}